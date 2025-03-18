package com.techtez.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eid;

    private String employeeName;

    private String designation;

    @ManyToOne
    @JsonBackReference // Prevents infinite recursion during JSON serialization
    @JoinColumn(name = "company_id") // Foreign key column in the Employee table
    private Company company;

    // Constructors
    public Employee() {
        super();
    }

    public Employee(int eid, String employeeName, String designation, Company company) {
        super();
        this.eid = eid;
        this.employeeName = employeeName;
        this.designation = designation;
        this.company = company;
    }

    // Getters and Setters
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
