package com.assignment.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.assignment.entity.Rate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@EnableJpaRepositories("com.assignment.repository")
public class RateRepoTests {
	
	@Mock
	private RateRepository rateRepository;

	@Test
	void testGetAllRates() {
		Rate rate = getRateEntity();
		rateRepository.save(rate);
		assertNotNull(rate.getRateId());

		List<Rate> rates = rateRepository.findAll();
		assertTrue(rates.size() > 0);
	}
	
	@Test
	void testSearchRateById() {
		Rate rate = getRateEntity();
		rateRepository.save(rate);
		assertNotNull(rate.getRateId());

		Rate savedRate = rateRepository.findByRateId(rate.getRateId());
		assertEquals(savedRate.getAmt(), 100);
	}

	@Test
	void testAddRate() {
		Rate rate = getRateEntity();
		rateRepository.save(rate);
		assertNotNull(rate.getRateId());
	}

	@Test
	void testUpdateRate() {
		Rate rate = getRateEntity();
		rateRepository.save(rate);
		assertNotNull(rate.getRateId());

		Rate r = rateRepository.findByRateId(rate.getRateId());
		assertEquals(r.getAmt(), 100);
		r.setAmt(200);
		
		r = rateRepository.save(r);
		assertEquals(r.getAmt(), 200);
	}

	@Test
	void testDeleteRate() {
		Rate rate = getRateEntity();
		rateRepository.save(rate);
		assertNotNull(rate.getRateId());

		rateRepository.delete(rate);
		Rate r = rateRepository.findByRateId(rate.getRateId());
		assertNull(r);
	}
	
	private Rate getRateEntity() {
		Rate rate = new Rate();
		rate.setRateId(15);
		rate.setRateDesc("YYY");
		rate.setRateEffDate(new Date());
		rate.setRateExpDate(new Date());
		rate.setAmt(100);
		return rate;
	}
}
