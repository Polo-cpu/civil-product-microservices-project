package com.civilproducts.orderservice.controller;

import com.civilproducts.orderservice.enums.Language;
import com.civilproducts.orderservice.exception.handler.GenericExceptionHandler;
import com.civilproducts.orderservice.model.entity.Order;
import com.civilproducts.orderservice.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderControllerTest {
    private static final Language language = Language.EN;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private OrderService orderService;
    @InjectMocks
    private OrderController orderController;
    @BeforeEach
    public void setup() {

        JacksonTester.initFields(this, new ObjectMapper());

        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }
    @Test
    void findAllOrders() throws Exception{
        EasyRandom generator = new EasyRandom();
        Order order1 = generator.nextObject(Order.class);
        Order order2 = generator.nextObject(Order.class);
        Order order3 = generator.nextObject(Order.class);
        Order order4 = generator.nextObject(Order.class);
        order1.setId(1L);
        order2.setId(2L);
        order3.setId(3L);
        order4.setId(4L);

        List<Order> sampleOrderList = new ArrayList<>();
        sampleOrderList.add(order1);
        sampleOrderList.add(order2);
        sampleOrderList.add(order3);
        sampleOrderList.add(order4);

        Mockito.when(orderService.allOrders(language)).thenReturn(sampleOrderList);
        MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(get("/order/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();
        List<Order> actualOrderList = new ObjectMapper()
                .readValue(mockHttpServletResponse.getContentAsString(), new TypeReference<>() {
                });
        Assertions.assertEquals(sampleOrderList.size(), actualOrderList.size());
    }
}
