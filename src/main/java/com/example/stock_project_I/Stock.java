package com.example.stock_project_I;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {

	private String symbol;
	private String companyName;
	private String primaryExchange;
	private float latestPrice;

	public Stock() {

	}

	public String getSymbol() {
		return symbol;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getPrimaryExchange() {
		return primaryExchange;
	}
	public float getLatestPrice() {
		return latestPrice;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setPrimaryExchange(String primaryExchange) {
		this.primaryExchange = primaryExchange;
	}
	public void setLatestPrice(float latestPrice) {
		this.latestPrice = latestPrice;
	}

	@Override
	public String toString() {
		return companyName + "'s latest price is " + latestPrice;
	}

}