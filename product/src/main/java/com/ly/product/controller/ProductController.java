package com.ly.product.controller;

import com.ly.product.entity.ProductEntity;
import com.ly.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping("/")
    public List<ProductEntity> products() {
        return productService.list();
    }


}
