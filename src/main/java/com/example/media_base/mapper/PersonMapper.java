package com.example.media_base.mapper;

import com.example.media_base.pojo.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PersonMapper {
    List<Person> search(String keyword);
}
