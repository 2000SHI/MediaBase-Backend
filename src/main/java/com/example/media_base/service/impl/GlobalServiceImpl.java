package com.example.media_base.service.impl;

import com.example.media_base.mapper.MediaMapper;
import com.example.media_base.mapper.PersonMapper;
import com.example.media_base.pojo.Media;
import com.example.media_base.pojo.PageBean;
import com.example.media_base.service.GlobalService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class GlobalServiceImpl implements GlobalService {

    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public PageBean<Object> search(Integer pageNum, Integer pageSize, List<String> types, String keyword) {
        List<Object> result = new ArrayList<>();
        boolean personSelected = types.contains("person");
        List<String> mediaTypes = new ArrayList<>();
        for (String type : types) {
            if (!"person".equals(type)) mediaTypes.add(type);
        }
        if (mediaTypes.isEmpty() && !personSelected) {
            result.addAll(mediaMapper.search(Arrays.asList("book", "movie", "music", "tv"), keyword));
            result.addAll(personMapper.search(keyword));
        }
        else {
            if (!mediaTypes.isEmpty()) {
                result.addAll(mediaMapper.search(mediaTypes, keyword));
            }
            if (personSelected) {
                result.addAll(personMapper.search(keyword));
            }
        }
        PageBean<Object> pb = new PageBean<>();
        pb.setTotal((long) result.size());
        pb.setItems(getPage(result, pageNum, pageSize));
        return pb;
    }

    private <T> List<T> getPage(List<T> list, Integer pageNum, Integer pageSize) {
        if (list.isEmpty()) return list;
        int len = list.size();
        int nPage = len / pageSize;
        if (pageSize * nPage < len) nPage++;
        pageNum = Math.min(pageNum, nPage);
        int start = (pageNum - 1) * pageSize;
        int end = Math.min(pageNum * pageSize, len);
        return list.subList(start, end);
    }
}
