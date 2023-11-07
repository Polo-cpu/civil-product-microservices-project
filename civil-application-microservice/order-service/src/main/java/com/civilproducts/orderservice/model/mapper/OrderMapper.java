package com.civilproducts.orderservice.model.mapper;

import com.civilproducts.orderservice.model.dto.OrderDTO;
import com.civilproducts.orderservice.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mappings({
            @Mapping(source = "orderNumber",target = "orderNumber"),
            @Mapping(source = "orderLineItemsList",target = "orderLineItemsList")})
    Order toOrderEntity(OrderDTO orderDTO);
}
