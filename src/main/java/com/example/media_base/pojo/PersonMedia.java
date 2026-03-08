package com.example.media_base.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PersonMedia {
    private Integer mediaId;
    private String title;
    private List<String> roles;
}
