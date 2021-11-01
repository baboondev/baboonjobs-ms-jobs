package com.baboondev.baboonjobsmsjobs.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document("Offer")
public class Offer {
    private String _id;
    private String job_id;
    private String user_id;
    private String description;
    private Double price;
    private List<Reply> replies;
    private Date createdAt;
    private Date updatedAt;

    public Offer(String description, String location, Date workDate) {

    }
}