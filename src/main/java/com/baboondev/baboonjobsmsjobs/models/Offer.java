package com.baboondev.baboonjobsmsjobs.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("offers")
public class Offer {
    @Id
    private String _id;
    private String jobId;
    private String authorId;
    private Double price;
    private Date createdAt;

}