package com.example.media_base.service.impl;

import com.example.media_base.mapper.MediaMapper;
import com.example.media_base.mapper.PersonMapper;
import com.example.media_base.pojo.*;
import com.example.media_base.service.MediaService;

import java.time.LocalDate;
import java.util.*;

import com.example.media_base.utils.ThreadLocalUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public PageBean<Media> list(
            Integer pageNum, Integer pageSize, String types
    ) {
        PageBean<Media> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);  // enable paging
        Page<Media> page;
        if (StringUtils.hasLength(types)) {
            List<String> typeList = new ArrayList<>();
            if (StringUtils.hasLength(types)) {
                typeList.addAll(Arrays.asList(types.split(",")));
            }
            page = (Page<Media>) mediaMapper.search(typeList, null);
        }
        else {
            page = (Page<Media>) mediaMapper.selectAll();
        }
        pb.setTotal(page.getTotal());
        pb.setItems(page.getResult());
//        System.out.println(pb);
        return pb;
    }

    @Override
    public Media findById(Integer id) {
        Media media = mediaMapper.findById(id);
        Integer rate = mediaMapper.getRateByMedia(id);
        media.setRate(rate);
        List<Comment> comments = mediaMapper.getComments(id);
        media.setComments(comments);
        return media;
    }

    @Override
    public Integer getRate(Integer mediaId) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        return mediaMapper.getRateByMediaAndUser(mediaId, userId);
    }

    @Override
    public String addRate(Integer mediaId, Integer score) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        Integer oldScore = mediaMapper.getRateByMediaAndUser(mediaId, userId);
        if (oldScore != null) return "rate already exist";
        mediaMapper.addRate(mediaId, userId, score);
        return null;
    }

    @Override
    public String updateRate(Integer mediaId, Integer score) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        Integer oldScore = mediaMapper.getRateByMediaAndUser(mediaId, userId);
        if (oldScore == null) return "rate not exist";
        mediaMapper.updateRate(mediaId, userId, score);
        return null;
    }

    @Override
    public List<Comment> getUserComments(Integer mediaId) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        return mediaMapper.getUserComments(mediaId, userId);
    }

    @Override
    public void addComment(Integer mediaId, String comment) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        mediaMapper.addComment(mediaId, userId, comment);
    }

    @Override
    public String deleteComment(Integer id) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        Comment comment = mediaMapper.findComment(id);
        if (comment == null) return "comment not exist";
        if (!Objects.equals(comment.getUserId(), userId)) return "cannot delete other user's comment";
        mediaMapper.deleteComment(id);
        return null;
    }

    @Override
    public List<MediaPerson> getPeople(Integer id) {
        return mediaMapper.getPeople(id);
    }

    @Override
    public String add(JsonNode data) {
        if (!data.has("type")) {
            return "field 'type' missing";
        }
        Media medium;
        String type = data.get("type").asText();
        switch (type) {
            case "book":
                medium = new Book();
                Book bookMedium = (Book) medium;
                if (!data.has("publisher")) {
                    return "field 'publisher' missing";
                }
                bookMedium.setPublisher(data.get("publisher").asText());
                break;
            case "movie":
                medium = new Movie();
                Movie movieMedium = (Movie) medium;
                if (!data.has("durationMinutes")) {
                    return "field 'durationMinutes' missing";
                }
                movieMedium.setDurationMinutes(data.get("durationMinutes").asInt());
                if (!data.has("rating")) {
                    return "field 'rating' missing";
                }
                String rating = data.get("rating").asText();
                switch (rating) {
                    case "Not Rated", "G", "PG", "PG-13", "R", "NC-17":
                        break;
                    default:
                        return "invalid 'rating' field: " + rating;
                }
                movieMedium.setRating(rating);
                break;
            case "music":
                medium = new Music();
                Music musicMedium = (Music) medium;
                if (!data.has("album")) {
                    return "field 'album' missing";
                }
                musicMedium.setAlbum(data.get("album").asText());
                if (!data.has("durationSeconds")) {
                    return "field 'durationSeconds' missing";
                }
                musicMedium.setDurationSeconds(data.get("durationSeconds").asInt());
                break;
            case "tv":
                medium = new Tv();
                Tv tvMedium = (Tv) medium;
                if (!data.has("seasons")) {
                    return "field 'seasons' missing";
                }
                tvMedium.setSeasons(data.get("seasons").asInt());
                break;
            default:
                return "invalid 'type' value: " + type;
        }
        medium.setType(type);
        if (!data.has("title")) {
            return "field 'title' missing";
        }
        if (data.has("description")) {
            medium.setDescription(data.get("description").asText());
        }
        if (!data.has("releaseDate")) {
            return "field 'releaseDate' missing";
        }
        medium.setTitle(data.get("title").asText());
        String releaseDateStr = data.get("releaseDate").asText();
        try {
            medium.setReleaseDate(LocalDate.parse(releaseDateStr));
        } catch (Exception e) {
            return "invalid 'releaseDate' value: " + releaseDateStr;
        }
        mediaMapper.add(medium);
        int mediaId = medium.getId();
        switch (type) {
            case "book":
                mediaMapper.addBook((Book) medium);
                break;
            case "movie":
                mediaMapper.addMovie((Movie) medium);
                break;
            case "music":
                mediaMapper.addMusic((Music) medium);
                break;
            case "tv":
                mediaMapper.addTv((Tv) medium);
                break;
        }
        if (data.has("contributor")) {
            for (JsonNode personNode : data.get("contributor")) {
                if (!personNode.has("role")) {
                    return "field 'role' of contributor missing";
                }
                String role = personNode.get("role").asText();
                String character = null;
                switch (role) {
                    case "writer", "director", "composer", "lyricist", "artist":
                        break;
                    case "cast":
                        if (personNode.has("characterName")) {
                            character = personNode.get("characterName").asText();
                            break;
                        }
                    default:
                        return "invalid 'role' value:" + role;
                }
                if (!personNode.has("id")) {
                    return "field 'id' of contributor missing";
                }
                int personId = personNode.get("id").asInt();
                mediaMapper.addPerson(personId, mediaId, role, character);
            }
        }
        return null;
    }
}
