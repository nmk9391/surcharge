package com.assignment.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.assignment.entity.Rate;
import com.assignment.excp.ResourceNotFoundExcp;
import com.assignment.repository.RateRepository;
import com.assignment.schemaobjects.RateSO;
import com.assignment.schemaobjects.SearchSO;
import com.assignment.schemaobjects.SurchargeSO;

@Service
public class RateService {

	@Autowired
	private RateRepository rateRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	static Logger logger=LogManager.getLogger(RateService.class);
	
	public List<RateSO> getAllRates(){
		List<RateSO> rateSOList=new ArrayList<>();
		List<Rate> rateList=rateRepository.findAll();
		if(CollectionUtils.isEmpty(rateList)) {
			logger.error("No rates found");
			throw new ResourceNotFoundExcp();	
		}
		logger.info("Total records fetched:"+rateList.size());
		for(Rate r:rateList) {
			RateSO rateSO=new RateSO();
			BeanUtils.copyProperties(r, rateSO);
			rateSOList.add(rateSO);
		}
		return rateSOList;
	}
	
	public SearchSO searchRate(Integer id){
		SearchSO searchSO=new SearchSO();
		Rate rate=rateRepository.findByRateId(id);
		if (rate == null) {
			logger.error("No rate found with id: " + id);
			throw new ResourceNotFoundExcp();
		}else {
			RateSO rateSO=new RateSO();
			BeanUtils.copyProperties(rate, rateSO);
			searchSO.setRateSO(rateSO);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<SurchargeSO> surchargeEntity = new HttpEntity<SurchargeSO>(headers);
			SurchargeSO surchargeSO = restTemplate.exchange("https://surcharge.free.beeceptor.com/surcharge", HttpMethod.GET, surchargeEntity, SurchargeSO.class).getBody();
			searchSO.setSurchargeSO(surchargeSO);
		}
		return searchSO;
	}
	
	public RateSO addRate(RateSO rateSO) {
		RateSO rateSOToReturn= new RateSO();
		Rate rate = new Rate();
		BeanUtils.copyProperties(rateSO, rate);
		rate = rateRepository.save(rate);
		BeanUtils.copyProperties(rate, rateSOToReturn);
		logger.info("Rate added successfully");
		return rateSOToReturn;
	}
	
	public RateSO updateRate(Integer rateid,RateSO rateSO) {
		RateSO rateSOToReturn= new RateSO();
		Rate rate = rateRepository.findByRateId(rateSO.getRateId());
		BeanUtils.copyProperties(rateSO, rate);
		rate = rateRepository.save(rate);
		BeanUtils.copyProperties(rate, rateSOToReturn);
		logger.info("Rate with ID:"+rateSO.getRateId()+" updated successfully");
		return rateSOToReturn;
	}
	
	public String deleteRate(Integer id) {
		Rate rate=rateRepository.findByRateId(id);
		if (rate == null) {
			logger.error("No rate found with id: " + id);
			throw new ResourceNotFoundExcp();
		}else {
			rateRepository.deleteById(id);
			logger.info("Rate removal success");
			return "Deleted";
		}
	}
}
