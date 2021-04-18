package com.example.demo.service.impl;

import com.example.demo.model.SimpleRSSEntry;
import com.example.demo.repository.RSSRepository;
import com.example.demo.service.RSSService;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class RSSServiceImpl implements RSSService {
    private final RSSRepository rssRepository;

    @Override
    public void updateNews(String urlAddress) {
        List<SimpleRSSEntry> rssEntryList = rssRepository.findAll(null, null);
        List<SimpleRSSEntry> simpleRSSEntries = readRemoteRss(urlAddress);
        List<SimpleRSSEntry> newItems = simpleRSSEntries.stream()
                .filter(item -> !rssEntryList.contains(item)).collect(Collectors.toList());
        newItems.forEach(rssRepository::save);
        log.info("news updated");
    }

    @Override
    public List<SimpleRSSEntry> find(String limit) {
        return rssRepository.findAll(limit, "received_time");
    }

    public List<SimpleRSSEntry> readRemoteRss(String urlAddress) {
        List<SimpleRSSEntry> simpleRSSEntries = new ArrayList<>();
        try {
            URL feedSource = new URL(urlAddress);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry item : entries) {
                String description = "";
                if (null != item.getDescription()) {
                    description = item.getDescription().getValue();
                }
                SimpleRSSEntry simpleRSSEntry = new SimpleRSSEntry(item.getTitle(), item.getLink(), description, null);
                simpleRSSEntries.add(simpleRSSEntry);
            }
            return simpleRSSEntries;
        } catch (IOException | FeedException ioe) {
            log.error("Something went wrong reading the rss");
        }
        return simpleRSSEntries;
    }

}
