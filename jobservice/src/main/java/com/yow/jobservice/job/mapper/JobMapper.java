package com.yow.jobservice.job.mapper;

import com.yow.jobservice.job.Job;
import com.yow.jobservice.job.dto.JobDTO;
import com.yow.jobservice.job.externals.Company;

public class JobMapper {

    public static JobDTO toDTO(Job job, Company company){
        JobDTO jobDTO = new JobDTO();

        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompany(company);

        return jobDTO;
    }
}
