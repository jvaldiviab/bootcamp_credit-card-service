package com.project.creditcardservice.models.request;

import com.project.creditcardservice.models.dto.Client;
import com.project.creditcardservice.models.dto.Transaction;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreditCardUpdateRequest {
    private Double fullGrantedAmount; // Monto max
    private Double availableAmount; // Monto available
}
