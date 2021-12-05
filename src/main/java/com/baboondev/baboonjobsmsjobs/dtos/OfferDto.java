package com.baboondev.baboonjobsmsjobs.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {
    private String jobId;
    private String authorId;
    private String description;
    private Double price;
}
