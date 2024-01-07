package com.onlineestore.product.infra.outputAdapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineestore.product.domain.Product;

@Repository
public interface IMySQLRepository extends JpaRepository<Product, Long> {

}
