package com.project.creditcardservice.models.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreditCardCreateRequest {
    private String clientId;
    private Double creditLine; // Monto max
    private int paymentDay; // Monto available
}
