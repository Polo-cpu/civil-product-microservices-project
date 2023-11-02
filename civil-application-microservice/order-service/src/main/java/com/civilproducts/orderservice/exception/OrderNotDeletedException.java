package com.civilproducts.orderservice.exception;

import com.civilproducts.orderservice.model.enums.Language;
import com.civilproducts.orderservice.utils.IMessageCodes;
import com.civilproducts.orderservice.utils.MessageUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class OrderNotDeletedException extends RuntimeException{
    private final Language language;
    private final IMessageCodes messageCodes;
    public OrderNotDeletedException(Language language, IMessageCodes messageCodes){
        super(MessageUtils.getMessage(language,messageCodes));
        this.language = language;
        this.messageCodes = messageCodes;
        log.error("[OrderNotDeletedException] -> message: {} developer message: {}",MessageUtils.getMessage(language,messageCodes),messageCodes);
    }
}
