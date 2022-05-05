package com.project.creditcardservice.models.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreditCardConsumeCreditCardRequest {
    private String creditCardNumber;
    private Double amount;
    private int numberInstallments;
}
