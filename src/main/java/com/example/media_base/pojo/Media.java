package com.example.media_base.pojo;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
public class Media {
    private Integer id;
    private String type;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private Instant createTime;
    private Double rate;
    private List<String> comments;

    public String toMediaString() {
        return "Media{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", createTime=" + createTime +
                '}';
    }
}
