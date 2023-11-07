package com.civilproducts.orderservice.exception;


import com.civilproducts.orderservice.enums.Language;
import com.civilproducts.orderservice.utils.IMessageCodes;
import com.civilproducts.orderservice.utils.MessageUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class OrderNotCreatedException extends RuntimeException{
    private final Language language;
    private final IMessageCodes messageCodes;
    public OrderNotCreatedException(Language language, IMessageCodes messageCodes){
        super(MessageUtils.getMessage(language,messageCodes));
        this.language = language;
        this.messageCodes = messageCodes;
        log.error("[OrderNotCreatedException] -> message: {} developer message: {}",MessageUtils.getMessage(language,messageCodes),messageCodes);
    }
}
