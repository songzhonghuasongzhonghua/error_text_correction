package com.example.error_correction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@MapperScan( "com.example.error_correction.mapper")
@SpringBootApplication
public class ErrorCorrectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErrorCorrectionApplication.class, args);
    }

}
