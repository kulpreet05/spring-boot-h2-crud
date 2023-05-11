package com.ks.springbooth2crud.controller;

import com.ks.springbooth2crud.model.JobStatus;
import com.ks.springbooth2crud.repository.JobStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/job")
@Slf4j
public class APIController {
    @Autowired
    private JobStatusRepository jobStatusRepository;
    @GetMapping("/status/{id}")
    public ResponseEntity<String> getStatus(@PathVariable("id") long id) {
        log.info("getStatus Begin.");
        Optional<JobStatus> jobStatus = jobStatusRepository.findById(id);
        if(jobStatus.isPresent()) {
            return new ResponseEntity<>(String.valueOf(jobStatus.get().getStatus()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/start")
    @ResponseStatus(HttpStatus.CREATED)
    public JobStatus jobStart() {
        JobStatus jobStatus = JobStatus.builder()
                .name("MY_JOB" + System.currentTimeMillis())
                .status('I')
                .build();
        jobStatusRepository.save(jobStatus);
        return jobStatus;
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<JobStatus> jobComplete(@PathVariable("id") Long id) {
        Optional<JobStatus> jobStatusOptional = jobStatusRepository.findById(id);
        if(jobStatusOptional.isPresent()) {
            JobStatus jobStatus = jobStatusOptional.get();
            jobStatus.setStatus('C');
            jobStatusRepository.save(jobStatus);
            return new ResponseEntity<>(jobStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
