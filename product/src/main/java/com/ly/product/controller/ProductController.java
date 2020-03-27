package com.ly.product.controller;

import com.ly.commom.entity.ProductEntity;
import com.ly.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired


    @GetMapping("/product")
    public List<ProductEntity> products() {
        return productService.list();
    }

    @GetMapping("/product/{productId}")
    public ProductEntity findOne(@PathVariable Integer productId) {
        return productService.getById(productId);
    }

}
