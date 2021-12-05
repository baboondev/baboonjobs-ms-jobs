package com.baboondev.baboonjobsmsjobs.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("jobs")
public class Job {
    @Id
    private String _id;
    private String authorId;
    private String groupJob;
    private Date dateToWork;
    private String location;
    private String description;
    private List<Offer> offers;
    private Offer acceptedOffer;
    private Date createdAt;
}