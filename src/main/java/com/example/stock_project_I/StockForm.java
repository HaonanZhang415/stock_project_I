package com.example.stock_project_I;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StockForm {
	@NotNull
	@Size(min=2, max=30)
	private String stock;

	@NotNull
	@Size(min=2, max=30)
	private String exchange;

	public String getStock() {
		return stock;
	}
	public String getExchange() {
		return exchange;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String toString() {
		return "Stock(Symbol: " + stock + ", Exchange: " + exchange + ")";
	}
}