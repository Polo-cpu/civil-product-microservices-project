package com.civilproducts.orderservice.model.dto;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private List<OrderLineItemDTO> orderLineItemDTOList;
}
