package com.baboondev.baboonjobsmsjobs.controllers;

import com.baboondev.baboonjobsmsjobs.dtos.JobDto;
import com.baboondev.baboonjobsmsjobs.services.JobService;
import com.baboondev.baboonjobsmsjobs.util.JwtUtils;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT })
@RequestMapping("jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity saveJob(@RequestHeader Map<String, String> headers, @Validated @RequestBody JobDto createJobDTO) {
        try {
            String userRole = JwtUtils.getRoleByToken(headers.get("token"));
            String userId = JwtUtils.getUserIdByToken(headers.get("token"));

            if (!userRole.equals("employeer") && !userRole.equals("admin")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
            }

            return ResponseEntity.ok(jobService.saveJob(createJobDTO, userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity getAllJobs(@RequestHeader Map<String, String> headers) {
        try {
            JwtUtils.verifyToken(headers.get("token"));
            return ResponseEntity.ok(jobService.getAllJobs());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity getJobById(@RequestHeader Map<String, String> headers,
            @PathVariable(value = "id") String id) {
        try {
            JwtUtils.verifyToken(headers.get("token"));
            return ResponseEntity.ok(jobService.getJobById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/employeer/{id}")
    public ResponseEntity getJobsByEmplooyerId(@RequestHeader Map<String, String> headers,
            @PathVariable(value = "id") String employeerId) {
        try {
            String userRole = JwtUtils.getRoleByToken(headers.get("token"));

            if (!userRole.equals("employeer") && !userRole.equals("admin")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
            }

            return ResponseEntity.ok(jobService.getJobsByEmplooyerId(employeerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/employee/{id}")
    public ResponseEntity getJobsByEmplooyeId(@RequestHeader Map<String, String> headers,
            @PathVariable(value = "id") String employeeId) {
        try {
            String userRole = JwtUtils.getRoleByToken(headers.get("token"));

            if (!userRole.equals("employee") && !userRole.equals("admin")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
            }

            return ResponseEntity.ok(jobService.getJobsByEmplooyeId(employeeId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity deleteJob(@RequestHeader Map<String, String> headers, @PathVariable(value="id") String id) {
        try {
            Claims claims = JwtUtils.verifyToken(headers.get("token"));
            String authorId = claims.get("id").toString();
            Boolean result = jobService.deleteJob(id, authorId);
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
