package com.baboondev.baboonjobsmsjobs.repositories;

import com.baboondev.baboonjobsmsjobs.models.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {

}
