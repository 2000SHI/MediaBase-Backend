package com.example.media_base.service;

import com.example.media_base.pojo.PageBean;

import java.util.List;

public interface GlobalService {
    PageBean<Object> search(Integer pageNum, Integer pageSize, List<String> types, String keyword);
}
