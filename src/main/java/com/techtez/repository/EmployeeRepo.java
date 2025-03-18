package com.techtez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techtez.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>
{
    List<Employee> findByCompanyCid(int companyId); // Custom method to find employees by company ID
}
