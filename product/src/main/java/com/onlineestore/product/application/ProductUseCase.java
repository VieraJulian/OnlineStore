package com.onlineestore.product.application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.onlineestore.product.domain.Product;
import com.onlineestore.product.infra.dto.ProductDTO;
import com.onlineestore.product.infra.inputport.IProductInputPort;
import com.onlineestore.product.infra.outputport.IProductMethods;

@Service
public class ProductUseCase implements IProductInputPort {

    @Autowired
    private IProductMethods productMethods;

    @Value("${server.port}")
    private int port;

    @Override
    public ProductDTO createProduct(MultipartFile image, ProductDTO product) {
        String fileName = StringUtils.cleanPath(System.currentTimeMillis() + "-" + image.getOriginalFilename());

        String dirPath = "src/main/resources/uploads/";

        Path path = Paths.get(dirPath + fileName);

        try {
            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String fileUrl = "http://localhost:" + port + "/src/main/resources/uploads/" + fileName;

        Product productInfo = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .image(fileUrl)
                .build();

        Product productNew = productMethods.save(productInfo);

        return ProductDTO.builder()
                .id(productNew.getId())
                .name(productNew.getName())
                .description(productNew.getDescription())
                .price(productNew.getPrice())
                .stock(productNew.getStock())
                .image(productNew.getImage())
                .build();
    }

    @Override
    public ProductDTO updateProduct(Long id, MultipartFile image, ProductDTO product) {
        Product productFinded = productMethods.getById(id);

        if (productFinded == null) {
            return null;
        }

        if (image != null && !image.isEmpty()) {
            String fileName = StringUtils.cleanPath(System.currentTimeMillis() + "-" + image.getOriginalFilename());

            String dirPath = "src/main/resources/uploads/";

            Path path = Paths.get(dirPath + fileName);

            try {
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String fileUrl = "http://localhost:" + port + "/src/main/resources/uploads/" + fileName;

            String oldFileName = productFinded.getImage().replace(
                    "http://localhost:" + port + "/src/main/resources/uploads/",
                    "");

            Path oldFilePath = Paths.get(dirPath + oldFileName);

            try {
                Files.deleteIfExists(oldFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            productFinded.setImage(fileUrl);
        }

        productFinded.setName(product.getName());
        productFinded.setDescription(product.getDescription());
        productFinded.setStock(product.getStock());
        productFinded.setPrice(product.getPrice());

        Product productUpdated = productMethods.save(productFinded);

        return ProductDTO.builder()
                .id(productUpdated.getId())
                .name(productUpdated.getName())
                .description(productUpdated.getDescription())
                .price(productUpdated.getPrice())
                .image(productUpdated.getImage())
                .stock(productUpdated.getStock())
                .build();
    }

    @Override
    public ProductDTO detailsProduct(Long id) {
        Product productFinded = productMethods.getById(id);

        if (productFinded == null) {
            return null;
        }

        return ProductDTO.builder()
                .id(productFinded.getId())
                .name(productFinded.getName())
                .description(productFinded.getDescription())
                .price(productFinded.getPrice())
                .image(productFinded.getImage())
                .stock(productFinded.getStock())
                .build();
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> productsDB = productMethods.getProducts();

        List<ProductDTO> products = new ArrayList<>();

        for (Product p : productsDB) {
            ProductDTO productDTO = ProductDTO.builder()
                    .id(p.getId())
                    .name(p.getName())
                    .description(p.getDescription())
                    .price(p.getPrice())
                    .image(p.getImage())
                    .stock(p.getStock())
                    .build();

            products.add(productDTO);
        }

        return products;
    }

    @Override
    public String deleteProduct(Long id) {
        Product productDB = productMethods.getById(id);

        if (productDB == null) {
            return null;
        }

        String dirPath = "src/main/resources/uploads/";

        String fileName = productDB.getImage().replace(
                "http://localhost:" + port + "/src/main/resources/uploads/",
                "");

        Path oldFilePath = Paths.get(dirPath + fileName);

        try {
            Files.deleteIfExists(oldFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        productMethods.delete(id);

        return "Product deleted successfully!";
    }

    @Override
    public String updateStock(Long id, int quantity) {
        Product productDB = productMethods.getById(id);

        if (productDB == null) {
            return null;
        }

        if (productDB.getStock() >= quantity) {
            productDB.setStock(productDB.getStock() - quantity);
        }

        productMethods.save(productDB);

        return "Product updated successfully!";
    }

}
