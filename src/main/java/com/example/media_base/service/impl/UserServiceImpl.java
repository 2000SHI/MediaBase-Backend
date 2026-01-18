package com.example.media_base.service.impl;

import com.example.media_base.mapper.UserMapper;
import com.example.media_base.pojo.User;
import com.example.media_base.service.UserService;
import com.example.media_base.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByEmail(String email) {
        System.out.println("[service] find by email");
        return userMapper.findByEmail(email);
    }

    @Override
    public User findById(Integer id) {
        System.out.println("[service] find by id");
        return userMapper.findById(id);
    }

    @Override
    public void register(String username, String password, String email) {
        System.out.println("[service] register");
        String hashed = passwordEncoder.encode(password);
        if (StringUtils.hasLength(username)) {
            userMapper.addWithName(username, hashed, email);
        }
        else {
            userMapper.add(hashed, email);
            User user = userMapper.findByEmail(email);
            userMapper.updateName(user.getId(), "user_" + user.getId());
        }
    }

    @Override
    public void update(User user) {
        System.out.println("[service] update");
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatar) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        userMapper.updateAvatar(id, avatar);
    }

    @Override
    public void updatePwd(Integer id, String password) {
        String hashed = passwordEncoder.encode(password);
        userMapper.updatePwd(id, hashed);
    }
}
