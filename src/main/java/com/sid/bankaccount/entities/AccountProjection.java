package com.sid.bankaccount.entities;

import com.sid.bankaccount.enums.AccountType;

public interface AccountProjection {

    String getId();

    AccountType getType();
}
