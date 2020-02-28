package com.example.stock_project_I;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StockProjectIApplication {

	private static final Logger log = LoggerFactory.getLogger(StockProjectIApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StockProjectIApplication.class, args);
	}

}
