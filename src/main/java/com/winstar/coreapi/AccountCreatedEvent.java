package com.winstar.coreapi;

/**
 * account created event
 *
 * @author gradle
 */
public class AccountCreatedEvent {
    private String accountId;
    private int overDraftLimit;

    public AccountCreatedEvent(String accountId, int overDraftLimit) {
        this.accountId = accountId;
        this.overDraftLimit = overDraftLimit;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getOverDraftLimit() {
        return overDraftLimit;
    }
}
