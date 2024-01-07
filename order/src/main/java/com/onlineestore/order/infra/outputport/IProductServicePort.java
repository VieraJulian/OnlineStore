package com.onlineestore.order.infra.outputport;

import com.onlineestore.order.infra.dto.ProductDTO;

public interface IProductServicePort {

    public ProductDTO getProduct(Long id);

    public void setStock(Long id, int quantity);
}
