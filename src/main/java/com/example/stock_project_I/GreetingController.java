package com.example.stock_project_I;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class GreetingController implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")
	public String showForm(StockForm stockForm) {
		return "form";
	}
	
	@PostMapping("/")
	public String checkPersonInfo(@Valid StockForm stockForm, @ModelAttribute Stock stock, BindingResult bindingResult) {
		StockAPICaller stockAPICaller = new StockAPICaller();
		if (bindingResult.hasErrors()) {
			return "form";
		}
		Stock response = stockAPICaller.makeAPICall(stockForm.getStock());
		stock.setSymbol(response.getSymbol());
		stock.setCompanyName(response.getCompanyName());
		stock.setPrimaryExchange(response.getPrimaryExchange());
		stock.setLatestPrice(response.getLatestPrice());
		return "results";
	}
}