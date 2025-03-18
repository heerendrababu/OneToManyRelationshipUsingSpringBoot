package com.techtez.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;

    private String companyName;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference // Handles JSON serialization for the child objects
    private List<Employee> employees;

    // Constructors
    public Company() {
        super();
    }

    public Company(int cid, String companyName, List<Employee> employees) {
        super();
        this.cid = cid;
        this.companyName = companyName;
        this.employees = employees;
    }

    // Getters and Setters
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
//when the Employee objects are saved, their Company 
//reference is also automatically set via employee.setCompany(company) due to the setEmployees method in the Company class.
    public void setEmployees(List<Employee> employees) {
        // Set the bidirectional relationship
        for (Employee employee : employees) {
            employee.setCompany(this);
        }
        this.employees = employees;
    }
}
