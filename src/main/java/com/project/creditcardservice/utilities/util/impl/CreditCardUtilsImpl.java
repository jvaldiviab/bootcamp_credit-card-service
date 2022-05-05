package com.project.creditcardservice.utilities.util.impl;

import com.project.creditcardservice.models.entity.CreditCardEntity;
import com.project.creditcardservice.models.request.CreditCardCreateRequest;
import com.project.creditcardservice.utilities.constants.Constants;
import com.project.creditcardservice.utilities.util.ICreditCardUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class CreditCardUtilsImpl implements ICreditCardUtils {

    private Constants constants;

    @Override
    public CreditCardEntity creditCardCreateRequestToCreditCard(CreditCardCreateRequest creditCard) {
        return CreditCardEntity.builder()
                .creditCardNumber(UUID.randomUUID().toString())
                .status(constants.getStatusActive())
                .billingCycle(Integer.parseInt(constants.getCreditCardBillingCycle()))
                .creditLine(creditCard.getCreditLine())
                .availableAmount(Double.parseDouble(constants.getCreditCardInitAvailableAmount()))
                .paymentDay(creditCard.getPaymentDay())
                .membershipDate(new Date(System.currentTimeMillis()))
                .dueDate(new Date(System.currentTimeMillis()))
                .build();
    }
}
