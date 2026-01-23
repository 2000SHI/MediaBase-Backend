package com.example.media_base.service.impl;

import com.example.media_base.mapper.MediaMapper;
import com.example.media_base.pojo.Media;
import com.example.media_base.pojo.PageBean;
import com.example.media_base.service.MediaService;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaMapper mediaMapper;

    @Override
    public PageBean<Media> list(Integer pageNum, Integer pageSize) {
        PageBean<Media> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);  // enable paging
        Page<Media> page = (Page<Media>) mediaMapper.selectAll();
        pb.setTotal(page.getTotal());
        pb.setItems(page.getResult());
        return pb;
    }

    @Override
    public Media findById(Integer id) {
        Media media = mediaMapper.findById(id);
        System.out.println(media);
        Double rate = mediaMapper.getRate(id);
        media.setRate(rate);
//        List<String> comments = mediaMapper.getComments(id);
//        media.setComments(comments);
        return media;
    }
}
