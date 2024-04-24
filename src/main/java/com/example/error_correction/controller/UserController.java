package com.example.error_correction.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.error_correction.entity.Text;
import com.example.error_correction.entity.User;
import com.example.error_correction.entity.constants.Constants;
import com.example.error_correction.entity.request.ErrorCorrectionRequest;
import com.example.error_correction.entity.response.EnglishToolRes;
import com.example.error_correction.entity.response.LlmCorrectionRes;
import com.example.error_correction.mapper.TextMapper;
import com.example.error_correction.mapper.UserMapper;
import com.example.error_correction.utils.JwtUtil;
import com.example.error_correction.utils.Result;
import io.jsonwebtoken.Claims;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;


@RequestMapping("/user")
@RestController
public class UserController {
    private String text;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TextMapper textMapper;


    //更新个人信息
    @PostMapping("/info_edit")
    public Result updateUser(@RequestBody User user) {
        User userFromDB = userMapper.selectById(user.getId());
        //未找到用户
        if (userFromDB == null) {
            return Result.failed().message("用户不存在");
        }
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", user.getId());
        userMapper.update(user, updateWrapper);
        return Result.success();

    }


    //获取个人信息
    @GetMapping("/info")
    public Result getUser(HttpServletRequest request, HttpServletResponse response) {
       String token =  request.getHeader("Authorization");
       if (token == null){
           return Result.failed().message("token不能为空");
       }
        Claims claims =  JwtUtil.parseToken(token);
       if (claims == null){
           response.setStatus(401);
           return Result.failed().message("token解析失败");
       }

       User user = userMapper.findByUsername(claims.getSubject());
       if(user == null){
           return Result.failed().message("用户不存在");
       }
       return Result.success().data("info",user);

    }

    //中英文纠错
    @PostMapping("error_correction")
    public Result errorCorrection(int userId, int language, int type, String content, MultipartFile file) throws IOException {

        User userFromDB = userMapper.selectById(userId);
        if(userFromDB == null) {
            return Result.failed().message("用户不存在");
        }

        //中文纠错
        if(language == Constants.Chinese){

            //文件
            if(type == Constants.Word){
                this.text = extractTextFromDoc(file);
            }
            //文本
            if(type== Constants.Text){
               this.text = content;
            }
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://www.mulanai.com:8001/v1/nlp/corrector";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String requestBody = "{\"text\": \" " + this.text+"\"}";
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
            ResponseEntity<LlmCorrectionRes> response = restTemplate.postForEntity(url, request, LlmCorrectionRes.class);
            LlmCorrectionRes responseBody = response.getBody();

            //写入数据库
            Text text = new Text();
            text.setUserId(userId);
            text.setLanguage(language);
            text.setType(type);
            text.setContent(this.text);
            LlmCorrectionRes.Output output =responseBody.getOutput();
            System.out.println();
            text.setResult(output.getCorrected_text());
            textMapper.insert(text);

            return Result.success().data("result",output.getCorrected_text());

        }


        //英文纠错
        if(language == Constants.English){
            //文件
            if(type == Constants.Word){
                this.text = extractTextFromDoc(file);
            }
            //文本
            if(type== Constants.Text){
                this.text = content;
            }
            // 创建RestTemplate实例
            RestTemplate restTemplate = new RestTemplate();

            // 准备form data
            MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();

            map.add("language", "en-us");
            map.add("data", "{\"text\":\""+this.text+"\"}");

            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // 创建HTTP实体
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

            // 发送POST请求
            String url = "https://api.languagetool.org/v2/check?c=1&instanceId=25994%3A1713605102597&v=standalone"; // 替换为实际的接口URL
            EnglishToolRes response = restTemplate.postForObject(url, request, EnglishToolRes.class);
            EnglishToolRes.Match match =  response.getMatches().get(0);
            EnglishToolRes.Match.Replacement replacement =  match.getReplacements().get(0);


            //写入数据库
            Text text = new Text();
            text.setUserId(userId);
            text.setLanguage(language);
            text.setType(type);
            text.setContent(this.text);
            text.setResult(replacement.getValue());
            textMapper.insert(text);

            return Result.success().data("result",replacement.getValue());


        }

        return Result.failed().message("未携带类型");

    }




    // 提取doc文件中的文本内容的方法
    private String extractTextFromDoc(MultipartFile file) throws IOException {
        // 使用合适的库或工具来解析doc文件，提取文本内容
        // 这里可以使用Apache POI等库来处理doc文件
        // 示例代码可能如下：
        InputStream inputStream = file.getInputStream();
        HWPFDocument document = new HWPFDocument(inputStream);
        WordExtractor extractor = new WordExtractor(document);
        String[] paragraphs = extractor.getParagraphText();
        // 去掉结尾的"\r\n"
        for (int i = 0; i < paragraphs.length; i++) {
            paragraphs[i] = paragraphs[i].replace("\r\n", "");
        }
        return String.join("", paragraphs);
    }



}


