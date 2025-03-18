package com.techtez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techtez.entity.Company;
import com.techtez.entity.Employee;
import com.techtez.service.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

//ResponseEntity is a Spring class used to represent the entire HTTP response, including status code, headers, and body.
// ResponseEntity is present in the org.springframework.http package in Spring Framework.
    // Save or update a company
    @PostMapping("/save")
    public ResponseEntity<String> saveCompany(@RequestBody Company company) {
        companyService.saveCompany(company);
        return ResponseEntity.ok("Company and employees saved successfully!");
    }

    // Get all companies with their employees
    @GetMapping("/fetchAll")
    public ResponseEntity<?> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    // Get company details by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable int id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return ResponseEntity.ok(company);
        } else {
            return ResponseEntity.status(404).body("Company not found!");
        }
    }

 // Update only company name
    @PutMapping("/update-name/{id}")
    public ResponseEntity<String> updateCompanyName(@PathVariable int id, @RequestBody String companyName) {
        boolean isUpdated = companyService.updateCompanyName(id, companyName);
        if (isUpdated) {
            return ResponseEntity.ok("Company name updated successfully!");
        } else {
            return ResponseEntity.status(404).body("Company not found!");
        }
    }

    // Update only employee names
    @PutMapping("/update-employees/{id}")
    public ResponseEntity<String> updateEmployeeNames(@PathVariable int id, @RequestBody List<Employee> updatedEmployees) {
        boolean isUpdated = companyService.updateEmployeeNames(id, updatedEmployees);
        if (isUpdated) {
            return ResponseEntity.ok("Employee names updated successfully!");
        } else {
            return ResponseEntity.status(404).body("Company or Employees not found!");
        }
    }


    // Delete a company
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable int id) {
        boolean isDeleted = companyService.deleteCompany(id);
        if (isDeleted) {
            return ResponseEntity.ok("Company deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("Company not found!");
        }
    }
}
