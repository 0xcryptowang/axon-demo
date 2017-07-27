package com.winstar.coreapi;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * with deposit money command
 *
 * @author gradle
 */
public class WithDepositMoneyCommand {
    @TargetAggregateIdentifier
    private String accountId;
    private int amount;

    public WithDepositMoneyCommand(String accountId, int amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getAmount() {
        return amount;
    }
}
