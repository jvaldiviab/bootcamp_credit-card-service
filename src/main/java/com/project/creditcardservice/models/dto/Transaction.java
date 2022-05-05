package com.project.creditcardservice.models.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Transaction {
    private String transactionNumber = UUID.randomUUID().toString();
    private String type;
    private Date time;
    private Double amount;
}
