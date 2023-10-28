package com.civilproducts.productservice.utils;

public enum MessageCodes implements IMessageCodes{
    OK(990),
    ERROR(991),
    PRODUCT_NOT_CREATED(1000),
    PRODUCT_NOT_FOUND(1001),
    PRODUCT_NOT_DELETED(1003);

    private final int value;
    MessageCodes(int value){
        this.value = value;
    }
    @Override
    public int getMessage() {
        return value;
    }
}
