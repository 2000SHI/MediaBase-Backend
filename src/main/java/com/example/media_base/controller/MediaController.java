package com.example.media_base.controller;

import com.example.media_base.pojo.*;
import com.example.media_base.service.MediaService;
import com.example.media_base.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public Result<PageBean<Media>> list(
            Integer pageNum,
            Integer pageSize,
            @NotNull String types
        ) {
        System.out.println("[controller] list");
        PageBean<Media> pb = mediaService.list(
                pageNum, pageSize, types
        );
        return Result.success(pb);
    }

    @GetMapping("detail")
    public Result<Media> find(@NotNull Integer id) {
        System.out.println("[controller] detail");
        Media media = mediaService.findById(id);
        return Result.success(media);
    }

    @GetMapping("rate")
    public Result<Integer> getRate(@NotNull Integer mediaId) {
        System.out.println("[controller] rate");
        Integer rate = mediaService.getRate(mediaId);
        return Result.success(rate);
    }

    @PostMapping("rate")
    public Result addRate(@NotNull Integer mediaId, @NotNull @Min(0) @Max(10) Integer score) {
        System.out.println("[controller] add rate");
        String error = mediaService.addRate(mediaId, score);
        if (error != null) {
            return Result.failure(error);
        }
        return Result.success();
    }

    @PutMapping("rate")
    public Result updateRate(@NotNull Integer mediaId, @NotNull @Min(0) @Max(10) Integer score) {
        System.out.println("[controller] update rate");
        String error = mediaService.updateRate(mediaId, score);
        if (error != null) {
            return Result.failure(error);
        }
        return Result.success();
    }

    @GetMapping("comment")
    public Result<List<Comment>> getComment(@NotNull Integer mediaId) {
        System.out.println("[controller] get comment");
        List<Comment> comments = mediaService.getUserComments(mediaId);
        return Result.success(comments);
    }

    @PostMapping("comment")
    public Result addComment(@NotNull Integer mediaId, @NotNull String comment) {
        System.out.println("[controller] add comment");
        mediaService.addComment(mediaId, comment);
        return Result.success();
    }

    @DeleteMapping("comment")
    public Result deleteComment(@NotNull Integer id) {
        System.out.println("[controller] delete comment");
        String error = mediaService.deleteComment(id);
        if (error != null) {
            return Result.failure(error);
        }
        return Result.success();
    }

    @GetMapping("people")
    public Result<List<MediaPerson>> getPeople(@NotNull Integer id) {
        System.out.println("[controller] get people");
        List<MediaPerson> people = mediaService.getPeople(id);
        return Result.success(people);
    }

    @PostMapping("")
    public Result addMedium(@RequestBody JsonNode data) {
        System.out.println("[controller] add medium");
        if (!userService.isAdmin()) return Result.failure("Permission denied");
        String error = mediaService.add(data);
        if (error != null) {
            return Result.failure(error);
        }
        return Result.success();
    }
}
