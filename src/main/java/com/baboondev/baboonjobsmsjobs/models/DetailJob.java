package com.baboondev.baboonjobsmsjobs.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document("detailJob")
public class DetailJob {
    private String description;
    private String location;
    private Date workDate;

    public DetailJob(String description, String location, Date workDate) {
        this.description = description;
        this.location = location;
        this.workDate = workDate;
    }
}
