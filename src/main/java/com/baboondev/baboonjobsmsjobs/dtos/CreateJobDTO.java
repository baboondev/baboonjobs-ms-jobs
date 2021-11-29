package com.baboondev.baboonjobsmsjobs.dtos;

import com.baboondev.baboonjobsmsjobs.models.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobDTO {
    private String groupJob;
    private Date dateToWork;
    private Location location;
    private String description;
}
