package com.civilproducts.productservice.exception;

import com.civilproducts.productservice.response.InternalApiResponse;
import com.civilproducts.productservice.response.MessageResponse;
import com.civilproducts.productservice.utils.MessageCodes;
import com.civilproducts.productservice.utils.MessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Collections;

@RestControllerAdvice
public class GenericExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundException.class)
    public InternalApiResponse<String> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        return  InternalApiResponse.<String>builder()
                .messageResponse(MessageResponse.builder().title(MessageUtils.getMessage(productNotFoundException.getLanguage(), MessageCodes.ERROR))
                        .description(MessageUtils.getMessage(productNotFoundException.getLanguage(),productNotFoundException.getMessageCodes()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(productNotFoundException.getMessage()))
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotCreatedException.class)
    public InternalApiResponse<String> handleProductNotCreatedException(ProductNotCreatedException productNotCreatedException){
        return  InternalApiResponse.<String>builder()
                .messageResponse(MessageResponse.builder().title(MessageUtils.getMessage(productNotCreatedException.getLanguage(), MessageCodes.ERROR))
                        .description(MessageUtils.getMessage(productNotCreatedException.getLanguage(),productNotCreatedException.getMessageCodes()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(productNotCreatedException.getMessage()))
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotDeletedException.class)
    public InternalApiResponse<String> handleProductNotDeletedException(ProductNotDeletedException productNotDeletedException){
        return  InternalApiResponse.<String>builder()
                .messageResponse(MessageResponse.builder().title(MessageUtils.getMessage(productNotDeletedException.getLanguage(), MessageCodes.ERROR))
                        .description(MessageUtils.getMessage(productNotDeletedException.getLanguage(),productNotDeletedException.getMessageCodes()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(productNotDeletedException.getMessage()))
                .build();
    }
}
