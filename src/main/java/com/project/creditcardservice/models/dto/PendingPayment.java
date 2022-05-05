package com.project.creditcardservice.models.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PendingPayment {
    private Quote quote;
    private int numberInstallments;
    private Double interest;
    private Double finalAmount;
    private Date firstPay;
}
