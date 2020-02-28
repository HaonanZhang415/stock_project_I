package com.example.stock_project_I;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class StockAPICaller {

	private String stockSymbol = "AAPL";
	private String apiUrl1 = "https://sandbox.iexapis.com/stable/stock/AAPL/quote?token=Tpk_3baa626e1bb640e6967347e8432d531f";

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

	public Stock makeAPICall() {
		RestTemplate restTemplate = new RestTemplate();
		Stock stock = restTemplate.getForObject(apiUrl1, Stock.class);
		return stock;
	}

}