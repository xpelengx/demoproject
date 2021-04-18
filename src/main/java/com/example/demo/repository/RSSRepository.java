package com.example.demo.repository;

import com.example.demo.model.SimpleRSSEntry;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface RSSRepository {

    RowMapper<SimpleRSSEntry> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new SimpleRSSEntry(resultSet.getString("topic"),
                resultSet.getString("link"),
                resultSet.getString("description"),
                resultSet.getTimestamp("received_time"));
    };

    List<SimpleRSSEntry> findAll(String limit, String sortField);

    SimpleRSSEntry findOne(String id);

    int save(SimpleRSSEntry rssEntry);

    int delete(String id);
}
