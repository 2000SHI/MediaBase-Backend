package com.example.media_base.service;

import com.example.media_base.pojo.Media;
import com.example.media_base.pojo.PageBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MediaService {
    PageBean<Media> list(Integer pageNum, Integer pageSize);
    Media findById(Integer id);
}
