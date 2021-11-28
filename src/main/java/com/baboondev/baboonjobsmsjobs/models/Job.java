package com.baboondev.baboonjobsmsjobs.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document("Job")
public class Job {
    @Id
    private String _id;
    private String authorId;
    private String groupJob;
    private Date dateToWork;
    private Location location;
    private String description;
    private List<Offer> offers;
    private Offer acceptedOffer;
    private Date createdAt;
}