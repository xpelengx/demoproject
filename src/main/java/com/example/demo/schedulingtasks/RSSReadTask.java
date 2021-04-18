package com.example.demo.schedulingtasks;

import com.example.demo.service.RSSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class RSSReadTask {
    private final RSSService rssService;

    @Scheduled(fixedRate = 50000)
    public void readRss() {
        log.info("updating news");
        rssService.updateNews("http://rss.cnn.com/rss/edition.rss");
    }
}
