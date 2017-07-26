package com.winstar.account;

import com.winstar.coreapi.*;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * account
 *
 * @author gradle
 */
@Aggregate
@NoArgsConstructor
public class Account {

    @AggregateIdentifier
    private String accountId;
    /**
     * 帐目
     */
    private int balance;
    /**
     * 账户透支限额
     */
    private int overdraftLimit;

    /**
     * 帐号创建命令
     */
    @CommandHandler
    public Account(CreateAccountCommand createAccountCommand) {
        apply(new AccountCreatedEvent(createAccountCommand.getAccountId()
                , createAccountCommand.getOverDraftLimit()));
    }

    /**
     * 存款命令
     */
    @CommandHandler
    public void handle(WithDepositMoneyCommand withDepositMoneyCommand) {
        apply(new MoneyWithDepositEvent(accountId, withDepositMoneyCommand.getAmount()
                , balance + withDepositMoneyCommand.getAmount()));

    }

    /**
     * 取款命令
     */
    @CommandHandler
    public void handle(WithDrawMoneyCommand withDrawMoneyCommand) throws OverdrafitLimitExceededException {
        if (balance + overdraftLimit >= withDrawMoneyCommand.getAmount()) {
            apply(new MoneyWithDrawnEvent(accountId, withDrawMoneyCommand.getAmount()
                    , balance - withDrawMoneyCommand.getAmount()));
        } else {
            throw new OverdrafitLimitExceededException();
        }
    }

    /**
     * 帐号创建事件,初始化帐号
     */
    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.accountId = event.getAccountId();
        this.overdraftLimit = event.getOverDraftLimit();
    }

    /**
     * 存款操作,更新帐目
     */
    @EventSourcingHandler
    public void on(MoneyWithDepositEvent event) {
        this.balance = event.getBalance();
    }


    /**
     * 取款操作,更新帐目
     */
    @EventSourcingHandler
    public void on(MoneyWithDrawnEvent event) {
        this.balance = event.getBalance();
    }
}
