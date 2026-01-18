package com.example.media_base.controller;

import com.example.media_base.pojo.Media;
import com.example.media_base.service.GlobalService;
import com.example.media_base.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @GetMapping("list")
    public List<Media> list() {
        return mediaService.list();
    }

}
