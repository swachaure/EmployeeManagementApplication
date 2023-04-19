package com.employee.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.management.entity.TaxThreshold;

public interface TaxThresholdRepository extends JpaRepository<TaxThreshold, Integer> {
	
}
