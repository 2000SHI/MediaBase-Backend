package com.example.media_base.service.impl;

import com.example.media_base.mapper.MediaMapper;
import com.example.media_base.pojo.*;
import com.example.media_base.service.MediaService;

import java.util.*;

import com.example.media_base.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaMapper mediaMapper;

    @Override
    public PageBean<Media> list(
            Integer pageNum, Integer pageSize, String types
    ) {
        PageBean<Media> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);  // enable paging
        Page<Media> page;
        if (StringUtils.hasLength(types)) {
            List<String> typeList = new ArrayList<>();
            if (StringUtils.hasLength(types)) {
                typeList.addAll(Arrays.asList(types.split(",")));
            }
            page = (Page<Media>) mediaMapper.search(typeList, null);
        }
        else {
            page = (Page<Media>) mediaMapper.selectAll();
        }
        pb.setTotal(page.getTotal());
        pb.setItems(page.getResult());
//        System.out.println(pb);
        return pb;
    }

    @Override
    public Media findById(Integer id) {
        Media media = mediaMapper.findById(id);
        Double rate = mediaMapper.getRateByMedia(id);
        media.setRate(rate);
        List<Comment> comments = mediaMapper.getComments(id);
        media.setComments(comments);
        return media;
    }

    @Override
    public String addRate(Integer mediaId, Double score) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        Double oldScore = mediaMapper.getRateByMediaAndUser(mediaId, userId);
        if (oldScore != null) return "rate already exist";
        mediaMapper.addRate(mediaId, userId, score);
        return null;
    }

    @Override
    public String updateRate(Integer mediaId, Double score) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        Double oldScore = mediaMapper.getRateByMediaAndUser(mediaId, userId);
        if (oldScore == null) return "rate not exist";
        mediaMapper.updateRate(mediaId, userId, score);
        return null;
    }

    @Override
    public void addComment(Integer mediaId, String comment) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        mediaMapper.addComment(mediaId, userId, comment);
    }

    @Override
    public String deleteComment(Integer id) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        Comment comment = mediaMapper.findComment(id);
        if (comment == null) return "comment not exist";
        if (!Objects.equals(comment.getUserId(), userId)) return "cannot delete other user's comment";
        mediaMapper.deleteComment(id);
        return null;
    }

    @Override
    public List<MediaPerson> getPeople(Integer id) {
        return mediaMapper.getPeople(id);
    }
}
