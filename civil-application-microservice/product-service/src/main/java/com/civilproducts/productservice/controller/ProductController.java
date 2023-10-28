package com.civilproducts.productservice.controller;

import com.civilproducts.productservice.enums.Language;
import com.civilproducts.productservice.model.dto.ProductDTO;
import com.civilproducts.productservice.model.entity.Product;
import com.civilproducts.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private static final Language language = Language.EN;

    @Autowired
    private ProductService productService;
    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> allProducts = productService.findAllProducts(language);
        return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO){
        Product product = productService.createProduct(language, productDTO);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Optional<Product> productById = productService.findProductById(language, id);
        return new ResponseEntity<Product>(productById.get(), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        productService.deleteProductById(language, id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}
