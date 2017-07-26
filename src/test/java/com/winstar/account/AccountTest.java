package com.winstar.account;

import com.winstar.coreapi.*;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;


public class AccountTest {
    private FixtureConfiguration<Account> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(Account.class);
    }

    /**
     * 测试帐号创建
     */
    @Test
    public void testCreateAccount() throws Exception {
        fixture.givenNoPriorActivity()
                .when(new CreateAccountCommand("1234", 1000))
                .expectEvents(new AccountCreatedEvent("1234", 1000));
    }

    /**
     * 测试合理取款金额
     */
    @Test
    public void testWithDrawReasonableAmount() {
        fixture.given(new AccountCreatedEvent("1234", 1000))
                .when(new WithDrawMoneyCommand("1234", 600))
                .expectEvents(new MoneyWithDrawnEvent("1234", 600, -600));
    }

    /**
     * 测试不合理的取款
     */
    @Test
    public void testWithDrawAbsurdAmount() {
        fixture.given(new AccountCreatedEvent("1234", 1000))
                .when(new WithDrawMoneyCommand("1234", 1001))
                .expectNoEvents()
                .expectException(OverdrafitLimitExceededException.class);
    }

    /**
     * 测试2次取款
     */
    @Test
    public void testWithdrawTwice() {
        fixture.given(new AccountCreatedEvent("1234", 1000)
                , new MoneyWithDrawnEvent("1234", 999, -999))
                .when(new WithDrawMoneyCommand("1234", 2))
                .expectNoEvents()
                .expectException(OverdrafitLimitExceededException.class);
    }

    /**
     * 测试存款
     */
    @Test
    public void testWithDeposit() {
        fixture.given(new AccountCreatedEvent("1234", 1000))
                .when(new WithDepositMoneyCommand("1234", 500))
                .expectEvents(new MoneyWithDepositEvent("1234", 500, 500));
    }

}