package com.project.creditcardservice.models.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreditCardPaymentCreditCardRequest {
    private String creditCardNumber;
    private Double amount;
}
