package com.winstar.coreapi;


import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class WithDrawMoneyCommand {
    @TargetAggregateIdentifier
    private String accountId;
    private int amount;

    public WithDrawMoneyCommand(String accountId, int amount) {
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
