package com.example.media_base.service;

import com.example.media_base.pojo.Media;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MediaService {
    public List<Media> list();
}
