package com.example.media_base.controller;

import com.example.media_base.pojo.Media;
import com.example.media_base.pojo.PageBean;
import com.example.media_base.pojo.Person;
import com.example.media_base.pojo.Result;
import com.example.media_base.service.MediaService;
import com.example.media_base.service.PersonService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("detail")
    public Result<Person> find(Integer id) {
        Person person = personService.findById(id);
        return Result.success(person);
    }
}
