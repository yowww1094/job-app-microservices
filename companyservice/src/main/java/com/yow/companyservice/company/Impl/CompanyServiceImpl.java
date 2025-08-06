package com.yow.companyservice.company.Impl;

import com.yow.companyservice.company.Company;
import com.yow.companyservice.company.CompanyRepository;
import com.yow.companyservice.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompanyById(long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company createCompany(Company company) {
        try {
            companyRepository.save(company);
            return company;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Company updateCompany(long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if (companyOptional.isPresent()){
            Company company = companyOptional.get();

            company.setName(updatedCompany.getName());
            company.setEmail(updatedCompany.getEmail());
            company.setPhone(updatedCompany.getPhone());
            company.setAddress(updatedCompany.getAddress());
            try {
                companyRepository.save(company);
                return company;
            }catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Boolean deleteCompany(long id) {
        try {
            companyRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
