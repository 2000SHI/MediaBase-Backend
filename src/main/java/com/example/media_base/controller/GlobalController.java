package com.example.media_base.controller;

import com.example.media_base.pojo.PageBean;
import com.example.media_base.pojo.Result;
import com.example.media_base.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.InvalidAttributesException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@RestController
public class GlobalController {

    @Autowired
    private GlobalService globalService;

    @PostMapping("search")
    public Result<PageBean<Object>> search(
            Integer pageNum,
            Integer pageSize,
            @RequestBody Map<String, Object> map) throws InvalidAttributesException {
//        System.out.println(map);
        List<String> types = null;
        String keyword = null;
        try {
            types = (List<String>) map.get("types");
            keyword = (String) map.get("keyword");
        } catch (Exception e) {
            throw new InvalidAttributesException("please input a list of type, and a keyword string");
        }
//        System.out.println(types);
//        System.out.println(keyword);
        PageBean<Object> pb = globalService.search(pageNum, pageSize, types, keyword);
        return Result.success(pb);
    }
}
