package com.example.demo.repository;

import com.example.demo.model.SimpleRSSEntry;
import com.example.demo.schedulingtasks.RSSReadTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@MockBean(RSSReadTask.class)
public class RepositoryTest {
    @Autowired
    private RSSRepository rssRepository;

    @Test
    public void saveLoadTest() {
        SimpleRSSEntry rssEntry = new SimpleRSSEntry("Test", "test", "test", null);
        rssRepository.save(rssEntry);
        List<SimpleRSSEntry> findAll = rssRepository.findAll(null, null);
        Assert.assertEquals(1, findAll.size());
        Assert.assertNotNull(rssRepository.findOne("test"));
        Assert.assertTrue(findAll.contains(rssEntry));
    }
}
