package com.assignment.schemaobjects;

import java.util.Date;

public class RateSO {

	private Integer rateId;
	private String rateDesc;
	private Date rateEffDate;
	private Date rateExpDate;
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
