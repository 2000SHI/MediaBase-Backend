package com.example.media_base.pojo;

import lombok.Data;

@Data
public class Music extends Media {
    private String album;
    private Integer durationSeconds;
}
