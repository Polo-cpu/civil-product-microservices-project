package com.civilproducts.productservice.service;

import com.civilproducts.productservice.enums.Language;
import com.civilproducts.productservice.exception.ProductNotCreatedException;
import com.civilproducts.productservice.exception.ProductNotDeletedException;
import com.civilproducts.productservice.exception.ProductNotFoundException;
import com.civilproducts.productservice.model.dto.ProductDTO;
import com.civilproducts.productservice.model.entity.Product;
import com.civilproducts.productservice.model.mapper.ProductMapper;
import com.civilproducts.productservice.model.mapper.ProductMapperImpl;
import com.civilproducts.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.civilproducts.productservice.utils.MessageCodes;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final ProductMapper productMapper = new ProductMapperImpl();


    public List<Product> findAllProducts(Language language) {
        try {
            log.info("all products are showing");
            return productRepository.findAll();
        } catch (Exception e) {
            throw new ProductNotFoundException(language, MessageCodes.PRODUCT_NOT_FOUND);
        }
    }
    public Optional<Product> findProductById(Language language, Long id) {
        Optional<Product> byId = productRepository.findById(id);
        if(byId.isEmpty()){
            throw new ProductNotFoundException(language, MessageCodes.PRODUCT_NOT_FOUND);
        }
        else{
            log.info("Product has found!");
            return byId;
        }
    }
    public Product createProduct(Language language, ProductDTO productDTO) {
        try {
            log.info("Product added successfully!");
            return productRepository.save(productMapper.toProductEntity(productDTO));
        }catch (Exception e){
            throw  new ProductNotCreatedException(language,MessageCodes.PRODUCT_NOT_CREATED);
        }
    }
    public void deleteProductById(Language language, Long id) {
        Optional<Product> byId = productRepository.findById(id);
        if(byId.isEmpty()){
            throw new ProductNotDeletedException(language,MessageCodes.PRODUCT_NOT_DELETED);
        }
        else{
            log.error("Product deleted successfully!");
            productRepository.delete(byId.get());
        }
    }
}
