package com.example.stock_project_I;

import javax.validation.Valid;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

//import com.example.stock_project_I.storage.StorageFileNotFoundException;
//import com.example.stock_project_I.storage.StorageService


@Controller
public class GreetingController {

	/*@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}*/

	@GetMapping("/stocks")
	public String showForm(StockForm stockForm) {
		return "form";
	}
	
	@PostMapping("/stocks")
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

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/fileUpload")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files", storageService.loadAll().map( path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString()).build().toUri().toString()).collect(Collectors.toList()));

		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"").body(file);
	}

	@PostMapping("/fileUpload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		storageService.store(file);
		redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/fileUpload";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}





























