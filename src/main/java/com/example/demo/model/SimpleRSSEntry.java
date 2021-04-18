package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
public class SimpleRSSEntry {
    private String topic;
    private String link;
    private String Description;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Date creationDate;
}
