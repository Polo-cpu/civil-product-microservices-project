package com.civilproducts.orderservice.service;

import com.civilproducts.orderservice.enums.Language;
import com.civilproducts.orderservice.model.dto.OrderDTO;
import com.civilproducts.orderservice.model.entity.Order;
import com.civilproducts.orderservice.model.entity.OrderLineItem;
import com.civilproducts.orderservice.model.mapper.OrderMapper;
import com.civilproducts.orderservice.model.mapper.OrderMapperImpl;
import com.civilproducts.orderservice.repository.OrderRepository;
import org.hamcrest.Matchers;
import org.hibernate.generator.Generator;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderServiceTest {
    private static final Language language = Language.EN;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper = new OrderMapperImpl();
    @InjectMocks
    private OrderService orderService;
    @Test
    void createOrder(){
        EasyRandom generator = new EasyRandom();
        Order order = generator.nextObject(Order.class);

        Mockito.when(orderRepository.save(any())).thenReturn(order);
        OrderDTO orderDTO = OrderDTO.builder()
                .orderNumber(order.getOrderNumber())
                .orderLineItemsList(order.getOrderLineItemsList())
                .build();

        Order actualOrder = orderService.createOrder(language, orderDTO);
        Assertions.assertEquals(order.getOrderNumber(), actualOrder.getOrderNumber());
        Assertions.assertEquals(order.getOrderLineItemsList(), actualOrder.getOrderLineItemsList());
    }
    @Test
    void getAllOrders(){
        EasyRandom generator = new EasyRandom();
        List<Order> sampleOrders = generator.objects(Order.class, 5).toList();
        List<Order> actualOrders = orderRepository.findAll();

        Mockito.when(orderService.allOrders(language)).thenReturn(sampleOrders);

        for(int i = 0; i<actualOrders.size(); i++) {
            Order product1 = actualOrders.get(i);
            Order product2 = actualOrders.get(i);
            Assertions.assertEquals(product1.getOrderNumber(), product2.getOrderNumber());
            Assertions.assertEquals(product1.getOrderLineItemsList(), product2.getOrderLineItemsList());
        }
    }
    @Test
    void deleteOrderById(){
        EasyRandom generator = new EasyRandom();
        Order sampleOrder = generator.nextObject(Order.class);
        Long orderId = 1L;

        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.ofNullable(sampleOrder));

        Mockito.doNothing().when(orderRepository).deleteById(1L);

        orderService.deleteOrderById(language, orderId);
        Mockito.verify(orderRepository,Mockito.times(1)).deleteById(orderId);
    }
    @Test
    void findOrderById(){
        EasyRandom generator = new EasyRandom();
        Order sampleOrder = generator.nextObject(Order.class);
        Long orderId = 1L;

        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.ofNullable(sampleOrder));

        Optional<Order> actualOrder = orderService.findOrderById(language, orderId);

        assert sampleOrder != null;
        Assertions.assertEquals(sampleOrder.getOrderNumber(),actualOrder.get().getOrderNumber());
        Assertions.assertEquals(sampleOrder.getOrderLineItemsList(),actualOrder.get().getOrderLineItemsList());


    }


}
