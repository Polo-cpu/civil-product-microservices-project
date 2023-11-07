package com.civilproducts.orderservice.exception.handler;

import com.civilproducts.orderservice.exception.OrderNotCreatedException;
import com.civilproducts.orderservice.exception.OrderNotDeletedException;
import com.civilproducts.orderservice.exception.OrderNotFoundException;
import com.civilproducts.orderservice.response.InternalApiResponse;
import com.civilproducts.orderservice.response.MessageResponse;
import com.civilproducts.orderservice.utils.MessageCodes;
import com.civilproducts.orderservice.utils.MessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Collections;

@RestControllerAdvice
public class GenericExceptionHandler {
    @ExceptionHandler(OrderNotCreatedException.class)
    public InternalApiResponse<String> handleOrderNotCreatedException(OrderNotCreatedException orderNotCreatedException){
        return  InternalApiResponse.<String>builder()
                .messageResponse(MessageResponse.builder().title(MessageUtils.getMessage(orderNotCreatedException.getLanguage(), MessageCodes.ERROR))
                        .description(MessageUtils.getMessage(orderNotCreatedException.getLanguage(),orderNotCreatedException.getMessageCodes()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(orderNotCreatedException.getMessage()))
                .build();
    }
    @ExceptionHandler(OrderNotDeletedException.class)
    public InternalApiResponse<String> handleOrderNotDeletedException(OrderNotDeletedException orderNotDeletedException){
        return  InternalApiResponse.<String>builder()
                .messageResponse(MessageResponse.builder().title(MessageUtils.getMessage(orderNotDeletedException.getLanguage(), MessageCodes.ERROR))
                        .description(MessageUtils.getMessage(orderNotDeletedException.getLanguage(),orderNotDeletedException.getMessageCodes()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(orderNotDeletedException.getMessage()))
                .build();
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public InternalApiResponse<String> handleOrderNotFoundException(OrderNotFoundException orderNotFoundException){
        return  InternalApiResponse.<String>builder()
                .messageResponse(MessageResponse.builder().title(MessageUtils.getMessage(orderNotFoundException.getLanguage(), MessageCodes.ERROR))
                        .description(MessageUtils.getMessage(orderNotFoundException.getLanguage(),orderNotFoundException.getMessageCodes()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(orderNotFoundException.getMessage()))
                .build();
    }

}
