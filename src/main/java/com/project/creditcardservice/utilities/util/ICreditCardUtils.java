package com.project.creditcardservice.utilities.util;

import com.project.creditcardservice.models.entity.CreditCardEntity;
import com.project.creditcardservice.models.request.CreditCardCreateRequest;

public interface ICreditCardUtils {
    CreditCardEntity creditCardCreateRequestToCreditCard(CreditCardCreateRequest creditCard);
}
