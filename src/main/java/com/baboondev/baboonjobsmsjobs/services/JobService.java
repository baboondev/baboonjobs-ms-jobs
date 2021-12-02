package com.baboondev.baboonjobsmsjobs.services;

import com.baboondev.baboonjobsmsjobs.dtos.CreateJobDTO;
import com.baboondev.baboonjobsmsjobs.mappers.JobMapper;
import com.baboondev.baboonjobsmsjobs.models.Job;
import com.baboondev.baboonjobsmsjobs.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveFindOperation;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public Optional<Job> getById(String id){
        return jobRepository.findById(id);

    }

    public List<Job> getJobs(String groupJob){
        return jobRepository.findAll();
    }

    public Boolean deleteJob(String id, String authorId) throws Exception {
        Optional<Job> foundedJob = jobRepository.findById(id);
        if(!foundedJob.isPresent()) {
            throw new Exception("Job not found");
        }
        if(foundedJob.get().getAuthorId() != authorId){
            throw new Exception("You dont have permissions to delete");
        }
        jobRepository.deleteById(id);
        return true;
    }
    // helpers
    private boolean validateDateToWork(Date date){
        Calendar minValidDate = Calendar.getInstance();
        minValidDate.setTime(new Date());
        minValidDate.add(Calendar.DAY_OF_YEAR, 5);
        return date.before(minValidDate.getTime());

    }
}
