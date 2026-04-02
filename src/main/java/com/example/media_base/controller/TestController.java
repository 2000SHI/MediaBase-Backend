package com.example.media_base.controller;

import com.example.media_base.pojo.Result;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/test")
public class TestController {

    @GetMapping("")
    public String controllerTest() {
        return "controller test";
    }

    @GetMapping("access")
    public Result access(@RequestHeader(name = "Authorization") String token, HttpServletResponse response) {
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//            return Result.success("Hi, " + claims.get("username"));
//        } catch (Exception e) {
//            response.setStatus(401);
//            return Result.failure("Please login");
//        }
        return Result.success("Hello");
    }
}
