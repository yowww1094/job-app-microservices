package com.yow.jobservice.job;

import org.apache.logging.log4j.util.LazyBoolean;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    Job createJob(Job job);
    Job getJobById(Long id);
    Boolean deleteJob(Long id);
    Job updateJob(Long id, Job updatedJob);
}
