package com.winstar.coreapi;

/**
 * create account command
 *
 * @author gradle
 */
public class CreateAccountCommand {
    private String accountId;
    private int overDraftLimit;

    public CreateAccountCommand(String accountId, int overDraftLimit) {
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
