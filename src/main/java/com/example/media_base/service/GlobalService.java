package com.example.media_base.service;

import java.util.List;

public interface GlobalService {
    List<Object> search(List<String> types, String keyword);
}
