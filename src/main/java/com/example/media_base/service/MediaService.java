package com.example.media_base.service;

import com.example.media_base.pojo.Comment;
import com.example.media_base.pojo.Media;
import com.example.media_base.pojo.MediaPerson;
import com.example.media_base.pojo.PageBean;

import java.util.List;

public interface MediaService {
    PageBean<Media> list(
            Integer pageNum, Integer pageSize, String types
    );
    Media findById(Integer id);
    Integer getRate(Integer mediaId);
    String addRate(Integer mediaId, Integer rate);
    String updateRate(Integer mediaId, Integer score);
    List<Comment> getUserComments(Integer mediaId);
    void addComment(Integer mediaId, String comment);
    String deleteComment(Integer id);
    List<MediaPerson> getPeople(Integer id);
}
