package com.project.creditcardservice.utilities.util;

import com.project.creditcardservice.models.dto.Account;
import com.project.creditcardservice.models.response.AccountPassiveServiceResponse;

public interface IAccountUtils {
    Account accountPassiveServiceToAccount(AccountPassiveServiceResponse account);
}
