package org.xjtu.demo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderCountBo {
	String accountId;
	String accountType;
	String isIncome;
	Date beginCreateDate;
	Date endCreateDate;
	BigDecimal price;
	

	public OrderCountBo() {
		
	}

	
	
	
	

	public OrderCountBo(String accountId, String accountType, String isIncome, Date beginCreateDate, Date endCreateDate,
			BigDecimal price) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.isIncome = isIncome;
		this.beginCreateDate = beginCreateDate;
		this.endCreateDate = endCreateDate;
		this.price = price;
	}






	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public String getIsIncome() {
		return isIncome;
	}


	public void setIsIncome(String isIncome) {
		this.isIncome = isIncome;
	}


	public Date getBeginCreateDate() {
		return beginCreateDate;
	}


	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}


	public Date getEndCreateDate() {
		return endCreateDate;
	}


	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
