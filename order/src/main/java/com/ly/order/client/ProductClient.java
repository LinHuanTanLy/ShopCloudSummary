package com.ly.order.client;

import com.ly.commom.entity.ProductEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface ProductClient {
    @GetMapping("/api/product/{productId}")
    ProductEntity findOne(@PathVariable Integer productId);
}
