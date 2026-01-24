package com.example.media_base.service;

import com.example.media_base.pojo.Media;
import com.example.media_base.pojo.PageBean;

public interface MediaService {
    PageBean<Media> list(Integer pageNum, Integer pageSize);
    Media findById(Integer id);
    String addRate(Integer mediaId, Double rate);
    String updateRate(Integer mediaId, Double score);
    void addComment(Integer mediaId, String comment);
    String deleteComment(Integer id);
}
