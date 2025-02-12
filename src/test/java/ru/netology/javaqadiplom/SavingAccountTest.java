package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void testExclusionRateIsNegative() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                -5
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

    @Test
    public void testYearChangeBalanceMoreThanLimit() {
        SavingAccount account = new SavingAccount(
                11_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(11_000 * 5 / 100, account.yearChange());
    }
}
