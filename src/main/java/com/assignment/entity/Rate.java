package com.assignment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rate")
public class Rate {

	public Rate() {}
	
	@Id
	@Column(name="rate_id")
	private Integer rateId;
	
	@Column(name="rate_desc")
	private String rateDesc;
	
	@Column(name="rate_eff_date")
	private Date rateEffDate;
	
	@Column(name="rate_exp_date")
	private Date rateExpDate;
	
	@Column(name="amt")
	private Integer amt;

	public Integer getRateId() {
		return rateId;
	}

	public void setRateId(Integer rateId) {
		this.rateId = rateId;
	}

	public String getRateDesc() {
		return rateDesc;
	}

	public void setRateDesc(String rateDesc) {
		this.rateDesc = rateDesc;
	}

	public Date getRateEffDate() {
		return rateEffDate;
	}

	public void setRateEffDate(Date rateEffDate) {
		this.rateEffDate = rateEffDate;
	}

	public Date getRateExpDate() {
		return rateExpDate;
	}

	public void setRateExpDate(Date rateExpDate) {
		this.rateExpDate = rateExpDate;
	}

	public Integer getAmt() {
		return amt;
	}

	public void setAmt(Integer amt) {
		this.amt = amt;
	}
	
	
}
