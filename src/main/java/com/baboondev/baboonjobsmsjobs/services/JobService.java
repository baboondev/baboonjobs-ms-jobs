package com.baboondev.baboonjobsmsjobs.services;

import com.baboondev.baboonjobsmsjobs.dtos.JobDto;
import com.baboondev.baboonjobsmsjobs.mappers.JobMapper;
import com.baboondev.baboonjobsmsjobs.models.Job;
import com.baboondev.baboonjobsmsjobs.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

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

    private boolean validateDateToWork(Date date) {
        Calendar minValidDate = Calendar.getInstance();
        minValidDate.setTime(new Date());
        minValidDate.add(Calendar.DAY_OF_YEAR, 5);
        return date.before(minValidDate.getTime());

    }
}
