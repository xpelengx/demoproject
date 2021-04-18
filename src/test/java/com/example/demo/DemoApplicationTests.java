package com.example.demo;

import com.example.demo.schedulingtasks.RSSReadTask;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@MockBean(RSSReadTask.class)
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

}
