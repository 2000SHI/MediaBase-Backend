package com.example.media_base.mapper;

import com.example.media_base.pojo.Comment;
import com.example.media_base.pojo.Media;
import com.example.media_base.pojo.MediaPerson;
import com.example.media_base.pojo.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MediaMapper {
    List<Media> selectAll();
    List<Media> search(List<String> types, String keyword);
    Media findById(Integer id);
    @Select("select avg(score) from rate where media_id = #{id} group by media_id")
    Integer getRateByMedia(Integer id);
    @Select("select score from rate where media_id = #{mediaId} and user_id = #{userId}")
    Integer getRateByMediaAndUser(Integer mediaId, Integer userId);
    List<Comment> getComments(Integer id);
    @Insert("insert into rate (media_id, user_id, score) values (#{mediaId}, #{userId}, #{score})")
    void addRate(Integer mediaId, Integer userId, Integer score);
    @Update("update rate set score = #{score} where media_id = #{mediaId} and user_id = #{userId}")
    void updateRate(Integer mediaId, Integer userId, Integer score);
    List<Comment> getUserComments(Integer mediaId, Integer userId);
    @Insert("insert into comment (media_id, user_id, comment) values (#{mediaId}, #{userId}, #{comment})")
    void addComment(Integer mediaId, Integer userId, String comment);
    @Select("select * from comment where id = #{id}")
    Comment findComment(Integer id);
    @Delete("delete from comment where id = #{id}")
    void deleteComment(Integer id);
    List<MediaPerson> getPeople(Integer id);
}
