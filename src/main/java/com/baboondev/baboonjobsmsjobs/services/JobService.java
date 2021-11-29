package com.baboondev.baboonjobsmsjobs.services;

import com.baboondev.baboonjobsmsjobs.dtos.CreateJobDTO;
import com.baboondev.baboonjobsmsjobs.mappers.JobMapper;
import com.baboondev.baboonjobsmsjobs.models.Job;
import com.baboondev.baboonjobsmsjobs.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class JobService {

    @Autowired
    public JobRepository jobRepository;

    public Job saveJob(CreateJobDTO jobDTO, String authorId) throws Exception {
        if(validateDateToWork(jobDTO.getDateToWork())){
            throw new Exception("Invalid date");
        }
        Job job = JobMapper.mapToJob(jobDTO);
        job.setAuthorId(authorId);
        return jobRepository.save(job);
    }

    // helpers
    private boolean validateDateToWork(Date date){
        Calendar minValidDate = Calendar.getInstance();
        minValidDate.setTime(new Date());
        minValidDate.add(Calendar.DAY_OF_YEAR, 5);
        return date.before(minValidDate.getTime());

    }
}
