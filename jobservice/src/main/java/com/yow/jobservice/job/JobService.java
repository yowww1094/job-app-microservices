package com.yow.jobservice.job;

import com.yow.jobservice.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    Job createJob(Job job);
    JobDTO getJobById(Long id);
    Boolean deleteJob(Long id);
    Job updateJob(Long id, Job updatedJob);
}
