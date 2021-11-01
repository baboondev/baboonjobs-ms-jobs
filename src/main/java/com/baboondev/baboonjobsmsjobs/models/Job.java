package com.baboondev.baboonjobsmsjobs.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document("jobs")
public class Job {
    @Id
    private String _id;
    private String publisher_id;
    private String title;
    private String type;
    private DetailJob detail;
    private String status;
    private Offer accepted_offer;
    private Date createdAt;
    private Date updatedAt;

    public Job(String _id, String publisher_id, String title, String type, DetailJob detailJob, String status, Offer accepted_offer) {
        this._id = _id;
        this.publisher_id = publisher_id;
        this.title = title;
        this.type = type;
        this.detail = detailJob;
        this.status = status;
        this.accepted_offer = accepted_offer;
    }
}

