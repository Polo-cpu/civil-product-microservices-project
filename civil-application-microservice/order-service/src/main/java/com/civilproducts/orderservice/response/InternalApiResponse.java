package com.civilproducts.orderservice.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class InternalApiResponse<T> {
    private MessageResponse messageResponse;
    private List<T> payload;
    private boolean hasError;
    private List<String> errorMessages;
    private HttpStatus httpStatus;
}
