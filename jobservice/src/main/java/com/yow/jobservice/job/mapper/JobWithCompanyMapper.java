package com.yow.jobservice.job.mapper;

import com.yow.jobservice.job.Job;
import com.yow.jobservice.job.dto.JobWithCompanyDTO;
import com.yow.jobservice.job.externals.Company;

public class JobWithCompanyMapper {

    public static JobWithCompanyDTO toDTO(Job job, Company company){
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();

        jobWithCompanyDTO.setJob(job);
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }
}
