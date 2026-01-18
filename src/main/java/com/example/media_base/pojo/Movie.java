package com.example.media_base.pojo;

import lombok.Data;

@Data
public class Movie extends Media {
    private Integer durationMinutes;
    private String rating;
}
