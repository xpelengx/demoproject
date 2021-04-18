package com.example.demo.controller;

import com.example.demo.model.SimpleRSSEntry;
import com.example.demo.service.RSSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
@RequestMapping("rss")
public class RSSController {
    private final RSSService rssService;
    @GetMapping("/entry")
    public List<SimpleRSSEntry> rssEntries(@RequestParam(defaultValue = "10") String limit) {
        log.info("lookup started");
        return rssService.find(limit);
    }
}
