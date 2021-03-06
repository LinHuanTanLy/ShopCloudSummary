package com.ly.order.client;

import com.ly.commom.entity.ProductEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("product-service")
public interface ProductClient {
    @GetMapping("/api/product/{productId}")
    ProductEntity findOne(@PathVariable Integer productId);

    @PutMapping("/api/product/")
    boolean updateProduct(@RequestBody ProductEntity productEntity);
}
