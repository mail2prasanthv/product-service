package com.myproject.productservice.rest;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.myproject.productservice.model.Coupon;
import com.myproject.productservice.model.Product;
import com.myproject.productservice.repo.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

	@Autowired
	private ProductRepo repo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${couponService.url}")
	private String couponServiceURL;
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public Product createProduct(@RequestBody Product prd) {
		String url = couponServiceURL +prd.getCouponCode();
		Coupon coupon = restTemplate.getForObject(url, Coupon.class);
		BigDecimal mrp = prd.getPrice();
		BigDecimal discountedPrice = mrp.subtract(coupon.getDiscount());
		prd.setPrice(discountedPrice);
		return repo.save(prd);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public Product createProduct(@PathVariable("id") Long id) {
		Product products = repo.findById(id).orElse(null);
		return products;
	}
}
