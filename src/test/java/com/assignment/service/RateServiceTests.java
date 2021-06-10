package com.assignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.assignment.entity.Rate;
import com.assignment.repository.RateRepository;
import com.assignment.schemaobjects.RateSO;
import com.assignment.schemaobjects.SearchSO;
import com.assignment.schemaobjects.SurchargeSO;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes= {RateService.class})
@EnableJpaRepositories("com.assignment.repository")
public class RateServiceTests {

	@Mock
	private RateRepository rateRepository;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private RateService rateService;

	@Test
	void testSearchRate() throws Exception {

		Rate rate=getRateEntity();

		when(rateRepository.findByRateId(10)).thenReturn(rate);

		SurchargeSO surchargeSO = new SurchargeSO();
		surchargeSO.setSurcharge(300);
		ResponseEntity<SurchargeSO> surchargeBeanEntity = new ResponseEntity<SurchargeSO>(surchargeSO, HttpStatus.OK);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<SurchargeSO> surchargeEntity = new HttpEntity<SurchargeSO>(headers);
		Mockito.when(restTemplate.exchange("https://surcharge.free.beeceptor.com/surcharge", HttpMethod.GET, surchargeEntity, SurchargeSO.class)).thenReturn(surchargeBeanEntity);

		SearchSO searchSO = rateService.searchRate(10);
		assertEquals(100, searchSO.getRateSO().getAmt());

	}

	@Test
	void testAddRate() throws Exception {

		when(rateRepository.save(Mockito.any(Rate.class))).thenAnswer(i -> i.getArguments()[0]);

		RateSO rateSO=getRateSO();

		rateSO = rateService.addRate(rateSO);
		assertEquals(100, rateSO.getAmt());
	}

	@Test
	void testUpdateRate() throws Exception {

		when(rateRepository.save(Mockito.any(Rate.class))).thenAnswer(i -> i.getArguments()[0]);

		RateSO rateSO = getRateSO();
		rateSO.setRateId(1);
		rateSO = rateService.addRate(rateSO);
		assertEquals(100, rateSO.getAmt());
	}

	@Test
	void testDeleteRate() throws Exception {
		Rate rate = new Rate();
		rate.setRateId(10);
		when(rateRepository.findByRateId(10)).thenReturn(rate);
		rateService.deleteRate(10);
		Mockito.verify(rateRepository, times(1)).deleteById(rate.getRateId());
	}
	
	private Rate getRateEntity() {
		Rate rate = new Rate();
		rate.setRateId(10);
		rate.setRateDesc("YYY");
		rate.setRateEffDate(new Date());
		rate.setRateExpDate(new Date());
		rate.setAmt(100);
		return rate;
	}
	
	private RateSO getRateSO() {
		RateSO rate = new RateSO();
		rate.setRateId(10);
		rate.setRateDesc("YYY");
		rate.setRateEffDate(new Date());
		rate.setRateExpDate(new Date());
		rate.setAmt(100);
		return rate;
	} 
}
