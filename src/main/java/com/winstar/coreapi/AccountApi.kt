package com.winstar.coreapi

import org.axonframework.commandhandling.TargetAggregateIdentifier

class CreateAccountCommand(val accountId: String, val overDraftLimit: Int)
class WithDrawMoneyCommand(@TargetAggregateIdentifier val accountId: String, val amount: Int)
class WithDepositMoneyCommand(@TargetAggregateIdentifier val accountId: String, val amount: Int)

class AccountCreatedEvent(val accountId: String, val overDraftLimit: Int)
class MoneyWithDrawnEvent(val accountId: String, val amount: Int, val balance: Int)
class MoneyWithDepositEvent(val accountId: String, val amount: Int, val balance: Int)
