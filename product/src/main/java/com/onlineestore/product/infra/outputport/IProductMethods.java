package com.onlineestore.product.infra.outputport;

import java.util.List;

import com.onlineestore.product.domain.Product;

public interface IProductMethods {

    public Product save(Product product);

    public Product getById(Long id);

    public List<Product> getProducts();

    public void delete(Long id);
}
