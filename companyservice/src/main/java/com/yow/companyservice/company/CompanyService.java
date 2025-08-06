package com.yow.companyservice.company;

import java.util.List;


public interface CompanyService {
    List<Company> findAll();
    Company findCompanyById(long id);
    Company createCompany(Company company);
    Company updateCompany(long id, Company company);
    Boolean deleteCompany(long id);
}
