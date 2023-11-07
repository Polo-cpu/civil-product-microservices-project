package com.civilproducts.orderservice.model.dto;

import lombok.*;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderLineItemDTO {
    private Double price;
    private Integer quantity;
}
