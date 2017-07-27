package com.winstar.coreapi;

/**
 * money with deposit event
 *
 * @author gradle
 */
public class MoneyWithDepositEvent {
    private String accountId;
    private int amount;
    private int balance;

    public MoneyWithDepositEvent(String accountId, int amount, int balance) {
        this.accountId = accountId;
        this.amount = amount;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalance() {
        return balance;
    }
}
