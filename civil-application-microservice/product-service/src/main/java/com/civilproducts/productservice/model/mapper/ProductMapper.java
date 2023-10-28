package com.civilproducts.productservice.model.mapper;

import com.civilproducts.productservice.model.dto.ProductDTO;
import com.civilproducts.productservice.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "productName",target = "productName"),
            @Mapping(source = "productDetails",target = "productDetails"),
            @Mapping(source = "productPrice",target = "productPrice")})
    Product toProductEntity(ProductDTO productDto);
}
