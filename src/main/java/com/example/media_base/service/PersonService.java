package com.example.media_base.service;

import com.example.media_base.pojo.Media;
import com.example.media_base.pojo.Person;
import com.example.media_base.pojo.PersonMedia;

import java.util.List;

public interface PersonService {
    Person findById(Integer id);
    List<PersonMedia> getMedia(Integer id);
    void add(String name, String bio);
}
