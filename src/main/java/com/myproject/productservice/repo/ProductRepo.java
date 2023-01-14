package com.myproject.productservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.productservice.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
