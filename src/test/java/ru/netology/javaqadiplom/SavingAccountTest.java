package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {
    @Test
    public void testMinBalanceIsNegative() {
        SavingAccount account = new SavingAccount(
                2_000,
                -1_000,
                10_000,
                5
        );
        int expected =-1_000;
        int actual = account.minBalance;
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testExceptionRateIsNegative() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                -5
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> account.add(3_000));
    }

    @Test
    public void testExceptionMinBalanceMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                11_000,
                10_000,
                5
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> account.add(3_000));
    }

    @Test
    public void testExceptionInitialBalanceMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                11_000,
                1_000,
                10_000,
                5
        );
    }
        @Test
        public void testExceptionInitialBalanceLessThanMinBalance() {
            SavingAccount account = new SavingAccount(
                    500,
                    1_000,
                    10_000,
                    5
            );

        Assertions.assertThrows(IllegalArgumentException.class, () -> account.add(3_000));
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
        account.pay(2500);
        int actual = account.balance;
        int expected = -500;
        Assertions.assertEquals(expected, actual);
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
