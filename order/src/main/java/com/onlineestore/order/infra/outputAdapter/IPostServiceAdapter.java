package com.onlineestore.order.infra.outputAdapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.onlineestore.order.infra.dto.ProductDTO;
import com.onlineestore.order.infra.outputport.IProductServicePort;

@FeignClient(name = "product-service")
public interface IPostServiceAdapter extends IProductServicePort {

    @Override
    @GetMapping("/products/detail/{id}")
    public ProductDTO getProduct(@PathVariable Long id);

    @PutMapping("/products/stock/{id}/{quantity}")
    public void setStock(@PathVariable Long id, @PathVariable int quantity);
}
