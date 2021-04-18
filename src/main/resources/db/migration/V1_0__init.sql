
CREATE TABLE rss_entry
(
    topic VARCHAR2(200),
    link VARCHAR2(200) not null primary key,
    description VARCHAR2(1000),
    received_time TIMESTAMP,
    updated_time TIMESTAMP
);

