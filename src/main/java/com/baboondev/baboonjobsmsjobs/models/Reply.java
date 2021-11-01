package com.baboondev.baboonjobsmsjobs.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document("Reply")
public class Reply {
    private String _id;
    private String user_id;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    public Reply(String user_id, String description) {
        this.user_id = user_id;
        this.description = description;
    }
}