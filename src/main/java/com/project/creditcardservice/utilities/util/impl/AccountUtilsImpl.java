package com.project.creditcardservice.utilities.util.impl;

import com.project.creditcardservice.models.dto.Account;
import com.project.creditcardservice.models.dto.AccountType;
import com.project.creditcardservice.models.response.AccountPassiveServiceResponse;
import com.project.creditcardservice.utilities.util.IAccountUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountUtilsImpl implements IAccountUtils {

    @Override
    public Account accountPassiveServiceToAccount(AccountPassiveServiceResponse account){
        return Account.builder()
                .id(account.getId())
                .status(account.getStatus())
                .accountNumber(account.getAccountNumber())
                .accountType(AccountType.builder().description(account.getAccountType().getDescription()).type(account.getAccountType().getType()).build())
                .currentBalance(account.getCurrentBalance())
                .build();
    }


}
