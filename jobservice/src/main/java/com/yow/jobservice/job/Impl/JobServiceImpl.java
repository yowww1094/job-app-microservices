package com.yow.jobservice.job.Impl;

import com.yow.jobservice.job.Job;
import com.yow.jobservice.job.JobRepository;
import com.yow.jobservice.job.JobService;
import com.yow.jobservice.job.dto.JobWithCompanyDTO;
import com.yow.jobservice.job.externals.Company;
import com.yow.jobservice.job.mapper.JobWithCompanyMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>()
    private final JobRepository jobRepository;
    private final Long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();
        for (Job job : jobs){
            RestTemplate restTemplate = new RestTemplate();
            Company company = restTemplate.getForObject("http://localhost:9002/companies/" + job.getCompanyId(), Company.class);

            jobWithCompanyDTOs.add(JobWithCompanyMapper.toDTO(job, company));
        }
        return jobWithCompanyDTOs;
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
    public Job getJobById(Long id){
        return jobRepository.findById(id).orElse(null);
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
