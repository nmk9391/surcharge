package com.assignment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.schemaobjects.RateSO;
import com.assignment.schemaobjects.SearchSO;
import com.assignment.service.RateService;

@RestController
@RequestMapping(path = "/api/v1/surcharge")
public class RateController {

	@Autowired
	private RateService rateService;
	
	Logger logger = LoggerFactory.getLogger(RateController.class);
	
	@RequestMapping("/getAllRates")
	@PreAuthorize("isAuthenticated()")
	public List<RateSO> getAllRates() throws InterruptedException{
		return rateService.getAllRates();
	}
	
	@GetMapping(value="/searchRate/{rateid}")
	@PreAuthorize("isAuthenticated()")
	public SearchSO searchRate(@PathVariable("rateid") Integer rateid) throws InterruptedException{
		return rateService.searchRate(rateid);
	}
	
	@PostMapping(value="/addRate", produces="application/json",consumes="application/json")
	@PreAuthorize("isAuthenticated()")
	public RateSO addRate(@RequestBody RateSO rateSO) throws InterruptedException{    
		return rateService.addRate(rateSO);    
	}
	
	@PutMapping(value="/updateRate", produces="application/json",consumes="application/json")
	@PreAuthorize("isAuthenticated()")
	public RateSO updateRate(@RequestBody RateSO rateSO) throws InterruptedException{    
		return rateService.updateRate(rateSO.getRateId(),rateSO);    
	}
	
	@DeleteMapping(value="/deleteRate/{rateid}")
	@PreAuthorize("isAuthenticated()")
	public String deleteRate(@PathVariable("rateid") Integer rateid) throws InterruptedException{    
		return rateService.deleteRate(rateid);    
	}
	
}
