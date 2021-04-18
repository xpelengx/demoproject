package com.example.demo.integration;

import com.example.demo.model.SimpleRSSEntry;
import com.example.demo.repository.RSSRepository;
import com.example.demo.schedulingtasks.RSSReadTask;
import com.example.demo.service.RSSService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.spy;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@MockBean(RSSReadTask.class)
public class RSSIntegrationTest {
    @Autowired
    RSSService rssService;

    @Test
    public void readRSSTest() {
        List<SimpleRSSEntry> rssEntries=rssService.readRemoteRss("http://rss.cnn.com/rss/edition.rss");
        Assert.assertTrue(rssEntries.size()>0);
    }
}
