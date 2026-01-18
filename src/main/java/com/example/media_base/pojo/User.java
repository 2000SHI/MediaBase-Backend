package com.example.media_base.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class User {
    @NotNull
    private Integer id;
    @NotEmpty
    private String username;
    @JsonIgnore
    private String password;
    @NotEmpty
    @Email
    private String email;
    private String avatar;
    private Instant createTime;
    private Instant updateTime;
}
