package com.baboondev.baboonjobsmsjobs.controllers;

import com.baboondev.baboonjobsmsjobs.dtos.CreateJobDTO;
import com.baboondev.baboonjobsmsjobs.models.Job;
import com.baboondev.baboonjobsmsjobs.services.JobService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("jobs")
public class JobController {

    @Autowired
    public JobService jobService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity saveJob(@Validated @RequestBody CreateJobDTO createJobDTO) {
        try {
            String authorId = "111"; // get from JWT;
            Job job = jobService.saveJob(createJobDTO, authorId);
            return ResponseEntity.ok(job);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
