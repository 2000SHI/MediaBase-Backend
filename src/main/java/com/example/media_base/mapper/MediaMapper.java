package com.example.media_base.mapper;

import com.example.media_base.pojo.Media;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MediaMapper {
    List<Media> selectAll();
    List<Media> search(List<String> types, String keyword);
}
