package com.example.error_correction.entity.response;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public class ListRes<T> {
    private Page<T> list;


    @Override
    public String toString() {
        return "ListRes{" +
                "list=" + list +
                '}';
    }

    public Page<T> getList() {
        return list;
    }

    public void setList(Page<T> list) {
        this.list = list;
    }
}
