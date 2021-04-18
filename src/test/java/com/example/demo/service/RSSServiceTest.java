package com.example.demo.service;

import com.example.demo.model.SimpleRSSEntry;
import com.example.demo.repository.RSSRepository;
import com.example.demo.schedulingtasks.RSSReadTask;
import lombok.extern.slf4j.Slf4j;
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
public class RSSServiceTest {
    @Autowired
    RSSService rssService;
    @MockBean
    private RSSRepository rssRepository;

    @Test
    public void updateNewsTest() {
        RSSService spyRssService = spy(rssService);
        Mockito.doReturn(List.of(new SimpleRSSEntry("test", "test", "test", null)))
                .when(spyRssService).readRemoteRss(Mockito.any());
        Mockito.when(rssRepository.findAll(null, null)).then(Mockito.RETURNS_MOCKS);
        spyRssService.updateNews("TEST");
        Mockito.verify(rssRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(rssRepository).findAll(Mockito.any(), Mockito.any());
    }
}
