package com.example.demo.service;

import com.example.demo.model.SimpleRSSEntry;

import java.util.List;

public interface RSSService {
    void updateNews(String urlAddress);

    List<SimpleRSSEntry> find(String limit);

    List<SimpleRSSEntry> readRemoteRss(String urlAddress);
}
