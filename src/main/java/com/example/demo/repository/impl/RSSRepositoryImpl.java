package com.example.demo.repository.impl;

import com.example.demo.model.SimpleRSSEntry;
import com.example.demo.repository.RSSRepository;
import com.example.demo.repository.SqlConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@Component
@Slf4j
public class RSSRepositoryImpl implements RSSRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RSSRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        jdbcTemplate.setDataSource(dataSource);
    }

    @Override
    public List<SimpleRSSEntry> findAll(String limit, String sortField) {
        return jdbcTemplate.query(
                String.format(SqlConstants.FIND_ALL_RSS, sortField, limit), ROW_MAPPER);
    }

    @Override
    public SimpleRSSEntry findOne(String link) {
        return jdbcTemplate.queryForObject(
                SqlConstants.FIND_RSS,
                ROW_MAPPER, link);
    }

    @Override
    public int save(SimpleRSSEntry rssEntry) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return jdbcTemplate.update(SqlConstants.INSERT_RSS, rssEntry.getTopic(), rssEntry.getLink(), rssEntry.getDescription(), timestamp, timestamp);
    }

    @Override
    public int delete(String id) {
        log.error("Not supported yet.");
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
