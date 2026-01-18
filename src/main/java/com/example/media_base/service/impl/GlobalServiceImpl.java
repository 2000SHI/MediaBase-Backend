package com.example.media_base.service.impl;

import com.example.media_base.mapper.MediaMapper;
import com.example.media_base.mapper.PersonMapper;
import com.example.media_base.service.GlobalService;
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
    public List<Object> search(List<String> types, String keyword) {
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
        return result;
    }
}
