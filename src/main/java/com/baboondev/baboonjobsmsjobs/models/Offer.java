package com.baboondev.baboonjobsmsjobs.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document("Offer")
public class Offer {
    @Id
    private String _id;
    private String jobId;
    private String authorId;
    private Double price;
    private Date createdAt;

}