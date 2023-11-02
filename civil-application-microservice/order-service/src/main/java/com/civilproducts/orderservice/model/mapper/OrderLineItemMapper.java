package com.civilproducts.orderservice.model.mapper;

import com.civilproducts.orderservice.model.dto.OrderDTO;
import com.civilproducts.orderservice.model.dto.OrderLineItemDTO;
import com.civilproducts.orderservice.model.entity.Order;
import com.civilproducts.orderservice.model.entity.OrderLineItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface OrderLineItemMapper {
    @Mappings({
            @Mapping(source = "price",target = "price"),
            @Mapping(source = "quantity",target = "quantity")})
    OrderLineItem toOrderLineItemEntity(OrderLineItemDTO orderLineItemDTO);

}
