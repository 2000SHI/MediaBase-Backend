package com.example.media_base.service.impl;

import com.example.media_base.mapper.MediaMapper;
import com.example.media_base.pojo.Media;
import com.example.media_base.service.MediaService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaMapper mediaMapper;

    @Override
    public List<Media> list() {
        List<Media> result = mediaMapper.selectAll();
//        System.out.println(result);
        for (Media m : result) {
            System.out.println(m.toMediaString());
            System.out.println(m);
        }
        return mediaMapper.selectAll();
    }
}
