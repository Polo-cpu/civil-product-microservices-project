package com.civilproducts.productservice.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {
    private String title;
    private String description;
    private String buttonPositive;
}
