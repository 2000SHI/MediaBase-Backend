package com.example.media_base.pojo;

import lombok.Data;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@Data
public class Comment {
    private Integer id;
    private Integer userId;
    private Integer mediaId;
    private String username;
    private String comment;
}
