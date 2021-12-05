package com.baboondev.baboonjobsmsjobs.services;

import com.baboondev.baboonjobsmsjobs.dtos.JobDto;
import com.baboondev.baboonjobsmsjobs.dtos.OfferDto;
import com.baboondev.baboonjobsmsjobs.mappers.JobMapper;
import com.baboondev.baboonjobsmsjobs.mappers.OfferMapper;
import com.baboondev.baboonjobsmsjobs.models.Job;
import com.baboondev.baboonjobsmsjobs.models.Offer;
import com.baboondev.baboonjobsmsjobs.repositories.JobRepository;
import com.baboondev.baboonjobsmsjobs.repositories.OfferRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private OfferRepository offerRepository;

    public Job saveJob(JobDto jobDTO, String authorId) throws Exception {
        if (validateDateToWork(jobDTO.getDateToWork()))
            throw new Exception("Invalid date");

        Job job = JobMapper.mapToJob(jobDTO);
        job.setCreatedAt(new Date());
        job.setAuthorId(authorId);

        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<Job> getJobById(String id) {
        return jobRepository.findById(id);
    }

    public List<Job> getJobsByEmplooyerId(String emplooyerId) {
        return jobRepository.findByEmplooyerId(emplooyerId);
    }

    public List<Job> getJobsByEmplooyeId(String emplooyerId) {
        return jobRepository.findByEmplooyeId(emplooyerId);
    }

    public Job addOffer(OfferDto offer) throws Exception {
        Job job = jobRepository.findById(offer.getJobId()).get();
        
        if (job == null) {
            throw new Exception("Job not found");
        }
        
        Offer offerDB = offerRepository.save(OfferMapper.mapToOffer(offer));
        
        if (job.getOffers() == null) {
            job.setOffers(new ArrayList<>());
            job.getOffers().add(offerDB);
        } else {
            job.getOffers().add(offerDB);
        }

        return jobRepository.save(job);
    }

    public Job acceptOffer(Offer offer) throws Exception {
        Job job = jobRepository.findById(offer.getJobId()).get();
        
        if (job == null) {
            throw new Exception("Job not found");
        }
        
        job.setAcceptedOffer(offer);

        return jobRepository.save(job);
    }

    private boolean validateDateToWork(Date date) {
        Calendar minValidDate = Calendar.getInstance();
        minValidDate.setTime(new Date());
        minValidDate.add(Calendar.DAY_OF_YEAR, 5);
        return date.before(minValidDate.getTime());

    }
}
