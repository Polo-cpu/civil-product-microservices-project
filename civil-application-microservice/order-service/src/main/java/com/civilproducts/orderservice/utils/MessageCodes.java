package com.civilproducts.orderservice.utils;

public enum MessageCodes implements IMessageCodes{
    OK(1990),
    ERROR(1991),
    ORDER_NOT_CREATED(2000),
    ORDER_NOT_DELETED(2001),
    ORDER_NOT_FOUND(2003);

    private final int value;
    MessageCodes(int value){
        this.value = value;
    }
    @Override
    public int getMessage() {
        return value;
    }
}
