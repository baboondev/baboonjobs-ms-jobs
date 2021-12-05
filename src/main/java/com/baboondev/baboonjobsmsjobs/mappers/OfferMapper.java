package com.baboondev.baboonjobsmsjobs.mappers;

import java.util.Date;

import com.baboondev.baboonjobsmsjobs.dtos.OfferDto;
import com.baboondev.baboonjobsmsjobs.models.Offer;

public class OfferMapper {
    public static Offer mapToOffer(OfferDto offerDto) {
        Offer offer = new Offer();
        offer.setJobId(offerDto.getJobId());
        offer.setAuthorId(offerDto.getAuthorId());
        offer.setDescription(offerDto.getDescription());
        offer.setPrice(offerDto.getPrice());
        offer.setCreatedAt(new Date());
        return offer;
    }
}
