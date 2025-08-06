package com.yow.jobservice.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        List<Job> jobs = jobService.findAll();
        if (jobs != null) return new ResponseEntity<>(jobs, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job != null) return new ResponseEntity<>(job, HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job){
        Job newJob = jobService.createJob(job);
        if (newJob != null) return new ResponseEntity<>(newJob, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        Boolean jobs = jobService.deleteJob(id);
        if (jobs) return new ResponseEntity<>("Job deleted Successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.valueOf(500));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        Job job = jobService.updateJob(id, updatedJob);
        if (job != null) return new ResponseEntity<>(job, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
