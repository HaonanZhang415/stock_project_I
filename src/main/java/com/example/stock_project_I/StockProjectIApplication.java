package com.example.stock_project_I;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StockProjectIApplication {

	private static final Logger log = LoggerFactory.getLogger(StockProjectIApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StockProjectIApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Stock stock = restTemplate.getForObject("https://sandbox.iexapis.com/stable/stock/AAPL/quote?token=Tpk_3baa626e1bb640e6967347e8432d531f", Stock.class);
			log.info(stock.toString());
		};
	}

}
