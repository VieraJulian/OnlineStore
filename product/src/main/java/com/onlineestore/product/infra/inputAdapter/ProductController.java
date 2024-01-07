package com.onlineestore.product.infra.inputAdapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onlineestore.product.infra.dto.ProductDTO;
import com.onlineestore.product.infra.inputport.IProductInputPort;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private IProductInputPort port;

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@ModelAttribute ProductDTO product,
            @RequestParam("img") MultipartFile file) {
        try {
            ProductDTO productDTO = port.createProduct(file, product);

            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@ModelAttribute ProductDTO product, @PathVariable Long id,
            @RequestParam(value = "img", required = false) MultipartFile file) {
        try {

            ProductDTO productDTO = port.updateProduct(id, file, product);

            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ProductDTO> detailProduct(@PathVariable Long id) {
        try {
            ProductDTO productDTO = port.detailsProduct(id);

            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        try {
            List<ProductDTO> productsList = port.getProducts();

            return new ResponseEntity<>(productsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/stock/{id}/{quantity}")
    public ResponseEntity<String> updateStock(@PathVariable Long id, @PathVariable int quantity) {
        try {
            String msj = port.updateStock(id, quantity);

            return new ResponseEntity<>(msj, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            String msj = port.deleteProduct(id);

            return new ResponseEntity<>(msj, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
