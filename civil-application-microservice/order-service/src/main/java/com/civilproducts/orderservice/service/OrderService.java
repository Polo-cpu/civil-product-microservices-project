package com.civilproducts.orderservice.service;

import com.civilproducts.orderservice.enums.Language;
import com.civilproducts.orderservice.exception.OrderNotCreatedException;
import com.civilproducts.orderservice.exception.OrderNotDeletedException;
import com.civilproducts.orderservice.exception.OrderNotFoundException;
import com.civilproducts.orderservice.model.dto.OrderDTO;
import com.civilproducts.orderservice.model.entity.Order;
import com.civilproducts.orderservice.model.mapper.OrderMapper;
import com.civilproducts.orderservice.model.mapper.OrderMapperImpl;
import com.civilproducts.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.civilproducts.orderservice.utils.MessageCodes;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    private OrderMapper orderMapper;
    public List<Order> allOrders(Language language){
        try {
            log.info("all orders are showing");
            return orderRepository.findAll();
        } catch (Exception e) {
            throw new OrderNotFoundException(language, MessageCodes.ORDER_NOT_FOUND);
        }
    }
    public Order createOrder(Language language, OrderDTO orderDTO) {
        try {
            log.info("all orders are creating");
            Order order = orderMapper.toOrderEntity(orderDTO);
            return orderRepository.save(order);
        } catch (Exception e) {
            throw new OrderNotCreatedException(language, MessageCodes.ORDER_NOT_CREATED);
        }
    }
    public Optional<Order> findOrderById(Language language, Long id){
        try{
            log.info("order showing by its id");
            return orderRepository.findById(id);
        }catch (Exception e){
            throw new OrderNotFoundException(language, MessageCodes.ORDER_NOT_FOUND);
        }
    }
    public void deleteOrderById(Language language, Long id) {
        Optional<Order> orderById = orderRepository.findById(id);
        if (orderById.isEmpty()) {
            throw new OrderNotFoundException(language, MessageCodes.ORDER_NOT_FOUND);
        } else {
            try{
                log.info("Order has deleted");
                orderRepository.deleteById(id);
            } catch (Exception e){
                throw new OrderNotDeletedException(language,MessageCodes.ORDER_NOT_DELETED);
            }
        }
    }

}
