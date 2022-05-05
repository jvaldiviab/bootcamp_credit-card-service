package com.project.creditcardservice.models.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Account {
    private String id;
    private String status; // blocked or active
    private String accountNumber; // number auto-generate
    private AccountType accountType; // type of account
    private Double currentBalance; // current balance
}
