package com.civilproducts.productservice.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private String productName;
    private String productDetails;
    private Double productPrice;
}
