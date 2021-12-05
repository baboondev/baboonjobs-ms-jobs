package com.baboondev.baboonjobsmsjobs.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
    private String groupJob;
    private Date dateToWork;
    private String location;
    private String description;
}
