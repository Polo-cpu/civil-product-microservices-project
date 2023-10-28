package com.civilproducts.orderservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_line_items")
public class OrderLineItem {
    private Long id;
    private BigDecimal price;
    private Integer quantity;


}
