package com.onlinestore.product.infra.outputAdapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinestore.product.domain.Product;

@Repository
public interface IMySQLRepository extends JpaRepository<Product, Long> {

}
