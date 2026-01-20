package com.example.media_base.service;

import com.example.media_base.pojo.User;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    User findByEmail(String email);
    User findById(Integer id);
    void register(String username, String password, String email);
    void update(User user);
    void updateAvatar(String avatar);
    void updateAvatar(MultipartFile multipartFile);
    void updatePwd(Integer id, String password);
}
