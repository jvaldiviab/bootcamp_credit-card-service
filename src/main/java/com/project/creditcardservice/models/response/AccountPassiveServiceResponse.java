package com.project.creditcardservice.models.response;

import com.project.creditcardservice.models.dto.AccountType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AccountPassiveServiceResponse {
    private String id;
    private String status; // blocked or active
    private String accountNumber; // number auto-generate
    private AccountTypePassiveServiceResponse accountType; // type of account
    private Double currentBalance; // current balance
}
