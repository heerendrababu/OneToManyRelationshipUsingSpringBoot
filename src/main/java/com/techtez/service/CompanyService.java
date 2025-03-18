package com.techtez.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techtez.entity.Company;
import com.techtez.entity.Employee;
import com.techtez.repository.CompanyRepo;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepo companyRepository;

    // Save a company with its employees
    public void saveCompany(Company company) {
        // Check if a company with the same name exists
        Company existingCompany = companyRepository.findByCompanyName(company.getCompanyName());
        if (existingCompany != null) {
            // If company exists, update it instead of creating a new one
            company.setCid(existingCompany.getCid());
            companyRepository.save(company); // Save company and employees (cascades automatically)
        } else {
            companyRepository.save(company); // Save as a new company
        }
    }

    // Retrieve all companies with their employees
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Get a single company by ID
    public Company getCompanyById(int id) {
        return companyRepository.findById(id).orElse(null);
    }

 // Update only the company name
    public boolean updateCompanyName(int id, String companyName) {
        Company existingCompany = companyRepository.findById(id).orElse(null);
        if (existingCompany != null) {
            existingCompany.setCompanyName(companyName);
            companyRepository.save(existingCompany); // Save the updated company
            return true; // Success
        }
        return false; // Company not found
    }

    // Update employee names
    public boolean updateEmployeeNames(int companyId, List<Employee> updatedEmployees) {
        Company existingCompany = companyRepository.findById(companyId).orElse(null);
        if (existingCompany != null) {
            List<Employee> existingEmployees = existingCompany.getEmployees();

            // Update each employee's name (based on the list passed)
            for (Employee updatedEmployee : updatedEmployees) {
                for (Employee existingEmployee : existingEmployees) {
                    if (existingEmployee.getEid() == updatedEmployee.getEid()) {
                        existingEmployee.setEmployeeName(updatedEmployee.getEmployeeName());
                    }
                }
            }

            companyRepository.save(existingCompany); // Save the updated company with updated employees
            return true; // Success
        }
        return false; // Company not found
    }

    // Delete a company and return boolean indicating success
    public boolean deleteCompany(int id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true; // Success
        }
        return false; // Company not found
    }
}
