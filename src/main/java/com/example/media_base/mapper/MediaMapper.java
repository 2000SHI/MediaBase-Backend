package com.example.media_base.mapper;

import com.example.media_base.pojo.*;
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
    @Insert("""
            insert into media (type, title, description, release_date, create_time)
            values (#{type}, #{title}, #{description}, #{releaseDate}, now())
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer add(Media medium);
    @Insert("insert into book (media_id, publisher) values (#{id}, #{publisher})")
    void addBook(Book book);
    @Insert("insert into movie (media_id, duration_minutes, rating) values (#{id}, #{durationMinutes}, #{rating})")
    void addMovie(Movie movie);
    @Insert("insert into music (media_id, album, duration_seconds) values (#{id}, #{album}, #{durationSeconds})")
    void addMusic(Music music);
    @Insert("insert into tv (media_id, seasons) values (#{id}, #{seasons})")
    void addTv(Tv tv);
    @Insert("""
            insert into media_person (person_id, media_id, role, character_name)
            values (#{personId}, #{mediaId}, #{role}, #{character})
            """)
    void addPerson(Integer personId, Integer mediaId, String role, String character);
}
