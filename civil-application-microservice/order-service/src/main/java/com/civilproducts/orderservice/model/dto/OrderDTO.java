package com.civilproducts.orderservice.model.dto;

import com.civilproducts.orderservice.model.entity.OrderLineItem;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderDTO {
    private String orderNumber;
    private List<OrderLineItem> orderLineItemsList;
}
