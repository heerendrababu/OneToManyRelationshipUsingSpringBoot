package com.techtez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techtez.entity.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Integer>
{
	 // Automatically generates the query based on the method name
    Company findByCompanyName(String companyName);
}
