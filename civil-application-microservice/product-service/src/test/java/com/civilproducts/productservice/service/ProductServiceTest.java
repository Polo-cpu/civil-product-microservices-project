package com.civilproducts.productservice.service;

import com.civilproducts.productservice.enums.Language;
import com.civilproducts.productservice.model.dto.ProductDTO;
import com.civilproducts.productservice.model.entity.Product;
import com.civilproducts.productservice.model.mapper.ProductMapper;
import com.civilproducts.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductServiceTest {
    private static final Language language = Language.EN;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductService productService;

    @Test
    void createProduct(){
        Product product = sampleProductsList().get(0);
        product.setId(null);
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(product.getProductName());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setProductDetails(product.getProductDetails());

        Product actualProduct = productService.createProduct(language, productDTO);

        Mockito.verify(productRepository, Mockito.times(1)).save(actualProduct);
        Assertions.assertEquals(product.getProductName(), actualProduct.getProductName());
        Assertions.assertEquals(product.getProductPrice(), actualProduct.getProductPrice());
        Assertions.assertEquals(product.getProductDetails(), actualProduct.getProductDetails());
    }
    @Test
    void getAllProducts(){
        List<Product> sampleProducts = sampleProductsList();
        List<Product> actualProducts = productRepository.findAll();

        Mockito.when(productService.findAllProducts(language)).thenReturn(sampleProducts);

        List<Product> sortedActualProducts = actualProducts.stream().sorted(productComparator())
                .toList();
        List<Product> sortedSampleProducts = sampleProducts.stream().sorted(productComparator())
                .toList();
        for(int i = 0; i<sortedActualProducts.size(); i++) {
            Product product1 = sortedActualProducts.get(i);
            Product product2 = sortedSampleProducts.get(i);
            Assertions.assertEquals(product1.getProductDetails(), product2.getProductDetails());
            Assertions.assertEquals(product1.getProductPrice(), product2.getProductPrice());
            Assertions.assertEquals(product1.getProductName(), product2.getProductName());
        }
    }
    @Test
    void findProductsById(){
        Product product = sampleProductsList().get(0);
        Optional<Product> optionalProduct = Optional.of(product);
        Long productId = 1L;
        Mockito.when(productRepository.findById(any())).thenReturn(optionalProduct);
        Optional<Product> actualProduct = productService.findProductById(language,1L);

        Assertions.assertEquals(product.getProductName(), actualProduct.get().getProductName());
        Assertions.assertEquals(product.getProductDetails(), actualProduct.get().getProductDetails());
        Assertions.assertEquals(product.getProductPrice(), actualProduct.get().getProductPrice());
    }
    @Test
    void deleteProductById(){
        Product sampleProduct = sampleProductsList().get(1);
        Long productId = 2L;

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.ofNullable(sampleProduct));
        Mockito.doNothing().when(productRepository).deleteById(productId);

        productService.deleteProductById(language,2L);
        Mockito.verify(productRepository,Mockito.times(1)).deleteById(productId);
    }
    public List<Product> sampleProductsList(){
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product(1L,"steel","constructor_material",150.0);
        Product product2 = new Product(2L,"concrete","constructor_material",30.0);
        Product product3 = new Product(3L,"brick","constructor_material",20.0);
        Product product4 = new Product(4L,"hard_hat","security_material",10.0);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        return productList;
    }
    public Comparator<Product> productComparator(){
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }
}
