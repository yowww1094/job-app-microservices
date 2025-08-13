package com.yow.jobservice.job.Impl;

import com.yow.jobservice.job.Job;
import com.yow.jobservice.job.JobRepository;
import com.yow.jobservice.job.JobService;
import com.yow.jobservice.job.clients.CompanyClient;
import com.yow.jobservice.job.dto.JobDTO;
import com.yow.jobservice.job.externals.Company;
import com.yow.jobservice.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>()
    private final JobRepository jobRepository;
    private final CompanyClient companyClient;
    @Autowired
    private RestTemplate restTemplate;
    private final Long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();
        for (Job job : jobs){
            //Company company = restTemplate.getForObject("http://COMPANYSERVICE:9002/companies/" + job.getCompanyId(), Company.class);
            Company company = companyClient.getCompany(job.getCompanyId());
            jobDTOS.add(JobMapper.toDTO(job, company));
        }
        return jobDTOS;
    }

    @Override
    public Job createJob(Job job) {
        try {
            jobRepository.save(job);
            return job;
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public JobDTO getJobById(Long id){
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null){
            Company company = restTemplate.getForObject("http://COMPANYSERVICE:9002/companies/" + job.getCompanyId(), Company.class);
            return JobMapper.toDTO(job, company);
        }
        return null;
    }

    @Override
    public Boolean deleteJob(Long id){
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Job updateJob(Long id, Job updatedJob){
        Optional<Job> jobOptional = jobRepository.findById(id);

            if (jobOptional.isPresent()){
                Job job = jobOptional.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setLocation(updatedJob.getLocation());
                try{
                    jobRepository.save(job);
                    return job;
                }catch (Exception e){
                    return null;
                }

            }
        return null;
    }
}
