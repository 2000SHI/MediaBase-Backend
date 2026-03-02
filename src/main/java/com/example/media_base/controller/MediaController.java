package com.example.media_base.controller;

import com.example.media_base.pojo.Media;
import com.example.media_base.pojo.PageBean;
import com.example.media_base.pojo.Result;
import com.example.media_base.service.MediaService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @GetMapping("list")
    public Result<PageBean<Media>> list(
            Integer pageNum,
            Integer pageSize,
            @NotNull String types
        ) {
        PageBean<Media> pb = mediaService.list(
                pageNum, pageSize, types
        );
        return Result.success(pb);
    }

    @GetMapping("detail")
    public Result<Media> find(Integer id) {
        Media media = mediaService.findById(id);
        return Result.success(media);
    }

    @PostMapping("rate")
    public Result addRate(Integer mediaId, Double score) {
        String error = mediaService.addRate(mediaId, score);
        if (error != null) {
            return Result.failure(error);
        }
        return Result.success();
    }

    @PutMapping("rate")
    public Result updateRate(Integer mediaId, Double score) {
        String error = mediaService.updateRate(mediaId, score);
        if (error != null) {
            return Result.failure(error);
        }
        return Result.success();
    }

    @PostMapping("comment")
    public Result addComment(Integer mediaId, String comment) {
        mediaService.addComment(mediaId, comment);
        return Result.success();
    }

    @DeleteMapping("comment")
    public Result addComment(Integer id) {
        String error = mediaService.deleteComment(id);
        if (error != null) {
            return Result.failure(error);
        }
        return Result.success();
    }

}
