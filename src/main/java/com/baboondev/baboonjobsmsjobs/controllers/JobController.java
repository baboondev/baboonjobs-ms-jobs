package com.baboondev.baboonjobsmsjobs.controllers;

import com.baboondev.baboonjobsmsjobs.dtos.CreateJobDTO;
import com.baboondev.baboonjobsmsjobs.models.Job;
import com.baboondev.baboonjobsmsjobs.services.JobService;
import com.baboondev.baboonjobsmsjobs.util.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Claims;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("jobs")
public class JobController {

    @Autowired
    public JobService jobService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity saveJob(@RequestHeader Map<String, String> headers, @Validated @RequestBody CreateJobDTO createJobDTO) {
        try {
            Claims claims = JwtUtils.parseJWT(headers.get("token"));
            String authorId = claims.get("id").toString();
            Job job = jobService.saveJob(createJobDTO, authorId);
            return ResponseEntity.ok(job);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity getJob(@PathVariable(value="id") String id){
        try {
            return ResponseEntity.ok(jobService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public ResponseEntity getJobs(
            @RequestParam(value = "groupJob", required = false) String groupJob,
            @RequestParam(value = "authorId", required = false) String authorId,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "available", required = false) Boolean available,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int offset
    ){
        try {
            //List<Job> jobs = jobService.getJobs(groupJob, authorId, available, city, country, limit, offset);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity deleteJob(@RequestHeader Map<String, String> headers, @PathVariable(value="id") String id) {
        try {
            Claims claims = JwtUtils.parseJWT(headers.get("token"));
            String authorId = claims.get("id").toString();
            Boolean result = jobService.deleteJob(id, authorId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
