package com.winstar.coreapi;

/**
 * money with drawn event
 *
 * @author gradle
 */
public class MoneyWithDrawnEvent {

    private String accountId;
    private int amount;
    private int balance;

    public MoneyWithDrawnEvent(String accountId, int amount, int balance) {
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
