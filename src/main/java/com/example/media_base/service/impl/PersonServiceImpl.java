package com.example.media_base.service.impl;

import com.example.media_base.mapper.PersonMapper;
import com.example.media_base.pojo.Person;
import com.example.media_base.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;

    @Override
    public Person findById(Integer id) {
        return personMapper.findbyId(id);
    }
}
