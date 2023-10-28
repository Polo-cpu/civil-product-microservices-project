package com.civilproducts.productservice.controller;

import com.civilproducts.productservice.enums.Language;
import com.civilproducts.productservice.exception.GenericExceptionHandler;
import com.civilproducts.productservice.model.dto.ProductDTO;
import com.civilproducts.productservice.model.entity.Product;
import com.civilproducts.productservice.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductControllerTest {
    private static final Language language = Language.EN;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    @BeforeEach
    public void setup() {

        JacksonTester.initFields(this, new ObjectMapper());

        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }
    @Test
    void findAll() throws Exception{
        List<Product> productList = sampleProductsList();
        Mockito.when(productService.findAllProducts(language)).thenReturn(productList);

        MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(get("/product/all")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();
        assertThat(mockHttpServletResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<Product> actualProductList = new ObjectMapper().readValue(mockHttpServletResponse.getContentAsString(), new TypeReference<List<Product>>() {
        });

        assertEquals(productList.size(),actualProductList.size());

    }
    @Test
    void create()throws Exception{
        Product product = sampleProductsList().get(0);
        product.setId(null);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(product.getProductName());
        productDTO.setProductDetails(product.getProductDetails());
        productDTO.setProductPrice(product.getProductPrice());
        Mockito.when(productService.createProduct(language,productDTO)).thenReturn(product);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedProductJsonStr = ow.writeValueAsString(product);
        MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(post("/product/create")
                 .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedProductJsonStr))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();
        assertThat(mockHttpServletResponse.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
    @Test
    void findById() throws Exception{
        List<Product> expectedProductList = sampleProductsList();
        Mockito.when(productService.findProductById(language,1L)).thenReturn(Optional.ofNullable(expectedProductList.get(1)));

        MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(get("/product/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();
        assertThat(mockHttpServletResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        Product product = new ObjectMapper().readValue(mockHttpServletResponse.getContentAsString(), Product.class);
        Assertions.assertEquals(expectedProductList.get(1).getProductName(), product.getProductName());
        Assertions.assertEquals(expectedProductList.get(1).getProductPrice(), product.getProductPrice());
        Assertions.assertEquals(expectedProductList.get(1).getProductDetails(), product.getProductDetails());
    }
    @Test
    void deleteById()throws Exception{
        Product expectedProduct = sampleProductsList().get(0);
        Mockito.when(productService.findProductById(language,1L)).thenReturn(Optional.ofNullable(expectedProduct));
        MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(delete("/product/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();
        assertThat(mockHttpServletResponse.getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());

        Mockito.doNothing().when(productService).deleteProductById(language,1L);


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
}
