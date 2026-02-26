package com.example.media_base.controller;

import com.example.media_base.pojo.Result;
import com.example.media_base.pojo.User;
import com.example.media_base.service.UserService;
import com.example.media_base.utils.JwtUtil;
import com.example.media_base.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate template;

    @PostMapping("/register")
    public Result register(
            @Pattern(regexp = "^\\S{1,32}$") String username,
            @Pattern(regexp = "^\\S{6,16}$") String password,
            @Email String email) {
        System.out.println("[controller] register");
        User user = userService.findByEmail(email);
        if (user != null) {
            return Result.failure("User with this email already exist");
        }
        else {
            userService.register(username, password, email);
            return Result.success();
        }
    }

    @PostMapping("/login")
    public Result login(@Email String email, String password) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return Result.failure("User not existing");
        }
        else {
            if (passwordEncoder.matches(password, user.getPassword())) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", user.getId());
                claims.put("username", user.getUsername());
                String token = JwtUtil.genToken(claims);
                ValueOperations<String, String> operations = template.opsForValue();
                operations.set(token, token);
                return Result.success(token);
            }
            else {
                return Result.failure("Incorrect email or password");
            }
        }
    }

    @GetMapping("/info")
    public Result<User> info() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        return Result.success(userService.findById(id));
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        System.out.println(user);
        userService.update(user);
        return Result.success();
    }

//    @PatchMapping("updateAvatar")
//    public Result updateAvatar(@RequestParam @URL String avatar) {
//        userService.updateAvatar(avatar);
//        return Result.success();
//    }

    @PatchMapping("updateAvatar")
    public Result updateAvatar(MultipartFile avatar) {
        userService.updateAvatar(avatar);
        return Result.success();
    }

    @PatchMapping("updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> map, @RequestHeader("Authorization") String token) {
        String oldPwd = map.get("old_pwd");
        String newPwd = map.get("new_pwd");
        String rePwd = map.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd)) {
            return Result.failure("please enter your old password");
        }
        if (!StringUtils.hasLength(newPwd)) {
            return Result.failure("please enter your new password");
        }
        if (!StringUtils.hasLength(rePwd)) {
            return Result.failure("please re-enter your new password");
        }
        if (!newPwd.equals(rePwd)) {
            return Result.failure("2 passwords must be the same");
        }
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        User user = userService.findById(id);
        if (!passwordEncoder.matches(oldPwd, user.getPassword())) {
            return Result.failure("incorrect old password");
        }
        userService.updatePwd(id, newPwd);
        ValueOperations<String, String> operations = template.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }
}