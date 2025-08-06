package com.yow.companyservice.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        List<Company> companies = companyService.findAll();
        if (companies != null) {
            return new ResponseEntity<>(companies, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable long id) {
        Company company = companyService.findCompanyById(id);
        if (company != null) return new ResponseEntity<>(company, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company){
        Company newCompany = companyService.createCompany(company);
        if (newCompany != null) return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable long id,@RequestBody Company company){
        Company updatedCompany = companyService.updateCompany(id, company);
        if (updatedCompany != null) return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCompany(long id){
        Boolean deleteCompany = companyService.deleteCompany(id);
        if (deleteCompany) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.valueOf(500));
    }
}
