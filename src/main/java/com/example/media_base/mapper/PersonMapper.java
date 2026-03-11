package com.example.media_base.mapper;

import com.example.media_base.pojo.Person;
import com.example.media_base.pojo.PersonRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PersonMapper {
    @Select("SELECT * FROM person")
    List<Person> list();
    List<Person> search(String keyword);
    @Select("SELECT * from PERSON WHERE id = #{id}")
    Person findById(Integer id);
    List<PersonRole> getMedia(Integer id);
    @Insert("INSERT INTO person (name, bio) VALUES (#{name}, #{bio})")
    void add(Person person);
}
