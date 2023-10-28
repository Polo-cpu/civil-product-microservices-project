package com.civilproducts.productservice.exception;

import com.civilproducts.productservice.enums.Language;
import com.civilproducts.productservice.utils.IMessageCodes;
import com.civilproducts.productservice.utils.MessageUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ProductNotFoundException extends RuntimeException{
    private final Language language;
    private final IMessageCodes messageCodes;
    public ProductNotFoundException(Language language, IMessageCodes messageCodes){
        super(MessageUtils.getMessage(language,messageCodes));
        this.language = language;
        this.messageCodes = messageCodes;
        log.error("[ProductNotCreatedException] -> message: {} developer message: {}",MessageUtils.getMessage(language,messageCodes),messageCodes);
    }
}
