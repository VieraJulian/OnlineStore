package com.onlinestore.product.infra.outputAdapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlinestore.product.domain.Product;
import com.onlinestore.product.infra.outputport.IProductMethods;

@Component
public class UserRepository implements IProductMethods {

    @Autowired
    private IMySQLRepository repo;

    @Override
    public Product save(Product product) {
        return repo.save(product);
    }

    @Override
    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Product> getProducts() {
        return repo.findAll();
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
