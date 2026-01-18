package com.example.media_base.controller;

import com.example.media_base.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Stack;

@RestController
public class GlobalController {

    @Autowired
    private GlobalService globalService;

    @GetMapping("search")
    public List<Object> search(@RequestBody Map<String, Object> map) {
//        System.out.println(map);
        List<String> types = (List<String>) map.get("types");
        String keyword = (String) map.get("keyword");
//        System.out.println(types);
//        System.out.println(keyword);
        return globalService.search(types, keyword);
    }
}
