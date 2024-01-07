package com.onlineestore.product.infra.inputport;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.onlineestore.product.infra.dto.ProductDTO;

public interface IProductInputPort {

    public ProductDTO createProduct(MultipartFile image, ProductDTO product);

    public ProductDTO updateProduct(Long id, MultipartFile image, ProductDTO product);

    public ProductDTO detailsProduct(Long id);

    public List<ProductDTO> getProducts();

    public String deleteProduct(Long id);

    public String updateStock(Long id, int quantity);
}
