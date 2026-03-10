package com.example.media_base.service.impl;

import com.example.media_base.mapper.PersonMapper;
import com.example.media_base.pojo.Person;
import com.example.media_base.pojo.PersonMedia;
import com.example.media_base.pojo.PersonRole;
import com.example.media_base.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;

    @Override
    public Person findById(Integer id) {
        return personMapper.findById(id);
    }

    @Override
    public List<PersonMedia> getMedia(Integer id) {
        List<PersonRole> mediumList = personMapper.getMedia(id);
        Map<Integer, List<String>> roleMap = new HashMap<>();
        Map<Integer, String> titleMap = new HashMap<>();
        for (PersonRole medium : mediumList) {
            int mediaId = medium.getMediaId();
            if (!roleMap.containsKey(mediaId)) {
                roleMap.put(mediaId, new ArrayList<>());
                titleMap.put(mediaId, medium.getTitle());
            }
            roleMap.get(mediaId).add(medium.getRole());
        }
        List<PersonMedia> mediaList = new ArrayList<>();
        for (Integer mediaId : titleMap.keySet()) {
            PersonMedia media = new PersonMedia();
            media.setMediaId(mediaId);
            media.setTitle(titleMap.get(mediaId));
            media.setRoles(roleMap.get(mediaId));
            mediaList.add(media);
        }
        return mediaList;
    }

    @Override
    public void add(String name, String bio) {
        Person person = new Person();
        person.setName(name);
        person.setBio(bio);
        personMapper.add(person);
    }
}
