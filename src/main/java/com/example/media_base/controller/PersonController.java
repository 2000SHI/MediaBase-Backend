package com.example.media_base.controller;

import com.example.media_base.pojo.*;
import com.example.media_base.service.MediaService;
import com.example.media_base.service.PersonService;
import com.example.media_base.service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public Result<List<Person>> list() {
        List<Person> people = personService.list();
        return Result.success(people);
    }

    @GetMapping("detail")
    public Result<Person> find(@NotNull Integer id) {
        System.out.println("[controller] find");
        Person person = personService.findById(id);
        return Result.success(person);
    }

    @GetMapping("media")
    public Result<List<PersonMedia>> getMedia(@NotNull Integer id) {
        System.out.println("[controller] get media");
        List<PersonMedia> media = personService.getMedia(id);
        return Result.success(media);
    }

    @PostMapping()
    public Result add(@NotNull String name, String bio) {
        System.out.println("[controller] add person");
        if (!userService.isAdmin()) return Result.failure("Permission denied");
        personService.add(name, bio);
        return Result.success();
    }
}
