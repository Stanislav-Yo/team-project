package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void testMinBalanceIsNegative() {
        int initialBalance = 2_000;
        int minBalance = -1_000;
        int maxBalance = 10_000;
        int rate = 5;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });
    }

    @Test
    public void testExceptionRateIsNegative() {
        int initialBalance = 2_000;
        int minBalance = 1_000;
        int maxBalance = 10_000;
        int rate = -5;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });
    }

    @Test
    public void testExceptionMinBalanceMoreThanMaxBalance() {
        int initialBalance = 2_000;
        int minBalance = 11_000;
        int maxBalance = 10_000;
        int rate = 5;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });
    }

    @Test
    public void testExceptionInitialBalanceMoreThanMaxBalance() {
        int initialBalance = 11_000;
        int minBalance = 1_000;
        int maxBalance = 10_000;
        int rate = 5;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });
    }
        @Test
        public void testExceptionInitialBalanceLessThanMinBalance() {
            int initialBalance = 500;
            int minBalance = 1_000;
            int maxBalance = 10_000;
            int rate = 5;

            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new SavingAccount(initialBalance, minBalance, maxBalance, rate);
            });
    }

    @Test
    public void testPayIfAmountIsNegative() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        boolean expected = false;
        boolean actual = account.pay(-1_000);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPayRegular() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        boolean expected = true;
        boolean actual = account.pay(500);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPayRegularCheckingBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.pay(500);
        int actual = account.balance;
        int expected = 2000 - 500;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPayMoreThanLimitCheckingBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        int amount = 2_500;
        boolean expected = false;

        Assertions.assertEquals(expected,  account.pay(amount));

    }

    @Test
    public void testPayMoreThanLimit() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        boolean expected = false;
        boolean actual = account.pay(1_500);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void testYearChangeRegular() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(2_000 * 5 / 100, account.yearChange());
    }

}

