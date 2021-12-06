package com.baboondev.baboonjobsmsjobs.repositories;

import java.util.List;

import com.baboondev.baboonjobsmsjobs.models.Job;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {
  @Query("{ 'authorId' : ?0 }")
  List<Job> findByEmplooyerId(String employeerId);

  @Query("{ 'offers.authorId' : ?0 }")
  List<Job> findByEmplooyeId(String employeeId);
}
