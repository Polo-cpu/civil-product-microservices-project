package com.civilproducts.orderservice.controller;

import com.civilproducts.orderservice.enums.Language;
import com.civilproducts.orderservice.model.dto.OrderDTO;
import com.civilproducts.orderservice.model.entity.Order;
import com.civilproducts.orderservice.repository.OrderRepository;
import com.civilproducts.orderservice.response.InternalApiResponse;
import com.civilproducts.orderservice.service.OrderService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    private final Language language = Language.EN;

    @GetMapping("/all")
    public InternalApiResponse<List<Order>> findAll(){
        List<Order> orders = orderService.allOrders(language);
        List<List<Order>> payloadOrders = new ArrayList<>();
        payloadOrders.add(orders);
        return InternalApiResponse.<List<Order>>builder()
                .httpStatus(HttpStatus.OK)
                .payload(payloadOrders)
                .build();
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create")
    public InternalApiResponse<Order> create(@RequestBody OrderDTO orderDTO){
        List<Order> orders = new ArrayList<>();
        Order aOrder = orderService.createOrder(language, orderDTO);
        orders.add(aOrder);
        return InternalApiResponse.<Order>builder()
                .httpStatus(HttpStatus.OK)
                .payload(orders)
                .build();

    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{}")
    public InternalApiResponse<Order> findById(@PathVariable("id") Long id){
        List<Order> orderList = new ArrayList<>();
        Optional<Order> order = orderService.findOrderById(language,id);
        orderList.add(order.get());
        return InternalApiResponse.<Order>builder()
                .httpStatus(HttpStatus.OK)
                .payload(orderList)
                .build();
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public InternalApiResponse<Void> delete(@PathVariable("id") Long id){
        orderService.deleteOrderById(language, id);
        return InternalApiResponse.<Void>builder()
                .httpStatus(HttpStatus.OK)
                .build();
    }
}
