package com.example.stock_project_I;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class StockAPICaller {

	private String stockSymbol = "AAPL";
	private String apiUrl1 = "https://sandbox.iexapis.com/stable/stock/";
	private String apiUrl2 = "/quote?token=Tpk_3baa626e1bb640e6967347e8432d531f";

	public void StockAPICaller() {

	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public String getApiUrl1() {
		return apiUrl1;
	}

	public void setApiUrl1(String apiUrl1) {
		this.apiUrl1 = apiUrl1;
	}

	public String getApiUrl2() {
		return apiUrl2;
	}

	public void setApiUrl2(String apiUrl2) {
		this.apiUrl2 = apiUrl2;
	}

	public Stock makeAPICall(String symbol) {
		RestTemplate restTemplate = new RestTemplate();
		Stock stock = restTemplate.getForObject(apiUrl1 + symbol + apiUrl2, Stock.class);
		return stock;
	}

}