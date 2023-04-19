package com.employee.management.services;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.management.repository.TaxThresholdRepository;

@Service
@Transactional
public class TaxService {
	@Autowired(required = true)
	TaxThresholdRepository repository;

	public double calculateTax(BigDecimal grossIncome) {
		Optional<Double> tax = repository.findAll().stream().map(t -> t.calculateTax(grossIncome)).filter(t -> t != 0D).findFirst();
		return tax.get();

	}
}
