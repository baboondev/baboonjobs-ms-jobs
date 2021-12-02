package com.baboondev.baboonjobsmsjobs.mappers;

import com.baboondev.baboonjobsmsjobs.dtos.JobDto;
import com.baboondev.baboonjobsmsjobs.models.Job;

import java.util.Date;

public class JobMapper {
    public static Job mapToJob(JobDto jobDTO) {
        Job job = new Job();
        job.setCreatedAt(new Date());
        job.setGroupJob(jobDTO.getGroupJob());
        job.setDescription(jobDTO.getDescription());
        job.setLocation(jobDTO.getLocation());
        job.setDateToWork(jobDTO.getDateToWork());
        return job;
    }
}
