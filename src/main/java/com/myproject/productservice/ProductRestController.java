package com.myproject.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

	@Autowired
	private ProductRepo repo;
	
	@RequestMapping(value = "products", method = RequestMethod.POST)
	public Product createProduct(@RequestBody Product prd) {
		return repo.save(prd);
	}
}
