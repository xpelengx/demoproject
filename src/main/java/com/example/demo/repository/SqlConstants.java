package com.example.demo.repository;

public class SqlConstants {
    public static final String INSERT_RSS = "INSERT INTO rss_entry VALUES (?, ?, ?, ?, ?)";
    public static final String FIND_RSS = "select * from rss_entry where link = ?";
    public static final String FIND_ALL_RSS = "select * from rss_entry order by %s desc limit %s";
}
