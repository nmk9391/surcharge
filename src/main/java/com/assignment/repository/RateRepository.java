package com.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entity.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {

	Rate findByRateId(Integer rateId);
}
