package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldThrowExceptionIfRateIsNegative() {

        int initialBalance = 1_000;
        int creditLimit = 5_000;
        int rate = -15;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(initialBalance, creditLimit, rate);
        });
    }

    @Test
    public void shouldThrowExceptionIfCreditLimitIsNegative() {

        int initialBalance = 1_000;
        int creditLimit = -5_000;
        int rate = 15;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(initialBalance, creditLimit, rate);
        });
    }

    @Test
    public void shouldThrowExceptionIfInitialBalanceBelowCreditLimit() {

        int initialBalance = -6_000;
        int creditLimit = 5_000;
        int rate = 15;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(initialBalance, creditLimit, rate);
        });
    }

    @Test
    public void shouldCreateAccountWithValidParameters() {

        int initialBalance = 1_000;
        int creditLimit = 5_000;
        int rate = 15;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        int expectedBalance = 1_000;
        int expectedCreditLimit = 5_000;

        int actualBalance = account.getBalance();
        int actualCreditLimit = account.getCreditLimit();

        Assertions.assertEquals(expectedBalance, actualBalance);
        Assertions.assertEquals(expectedCreditLimit, actualCreditLimit);
    }

    @Test
    public void shouldAllowNegativeInitialBalanceWithinCreditLimit() {

        int initialBalance = -200;
        int creditLimit = 5_000;
        int rate = 15;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        int expectedBalance = -200;
        int actualBalance = account.getBalance();

        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void shouldPayIfAmountIsPositiveAndWithinLimit() {

        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15);
        int amount = 2_000;

        boolean result = account.pay(amount);

        boolean expectedResult = true;
        int expectedBalance = -1_000;
        int actualBalance = account.getBalance();

        Assertions.assertEquals(expectedResult, result);
        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void shouldNotPayIfAmountExceedsCreditLimit() {

        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15);
        int amount = 7_000;
        boolean result = account.pay(amount);

        boolean expectedResult = false;
        int expectedBalance = 1_000;
        int actualBalance = account.getBalance();

        Assertions.assertEquals(expectedResult, result);
        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void shouldNotPayIfAmountIsNegative() {

        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15);
        int amount = -2_000;
        boolean result = account.pay(amount);

        boolean expectedResult = false;
        int expectedBalance = 1_000;
        int actualBalance = account.getBalance();

        Assertions.assertEquals(expectedResult, result);
        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void shouldNotPayIfAmountIsZero() {

        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15);
        int amount = 0;
        boolean result = account.pay(amount);

        boolean expectedResult = false;
        int expectedBalance = 1_000;
        int actualBalance = account.getBalance();

        Assertions.assertEquals(expectedResult, result);
        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void shouldPayUpToCreditLimit() {

        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15);
        int amount = 5_000;
        boolean result = account.pay(amount);

        boolean expectedResult = true;
        int expectedBalance = -5_000;
        int actualBalance = account.getBalance();

        Assertions.assertEquals(expectedResult, result);
        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void shouldPayUpIfAmountIsLessThanCreditLimitByOne() {

        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15);
        int amount = 4_999;
        boolean result = account.pay(amount);

        boolean expectedResult = true;
        int expectedBalance = -4_999;
        int actualBalance = account.getBalance();

        Assertions.assertEquals(expectedResult, result);
        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void shouldNotPayIfAmountExceedsCreditLimitByOne() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15);
        int amount = 5_001;
        boolean result = account.pay(amount);

        boolean expectedResult = false;
        int expectedBalance = 0;
        int actualBalance = account.getBalance();

        Assertions.assertEquals(expectedResult, result);
        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                1000,
                5_000,
                15
        );
        account.add(2_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldNotAddIfAmountIsNegative() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        boolean result = account.add(-2_000);

        Assertions.assertFalse(result);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldNotAddIfAmountIsZero() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        boolean result = account.add(0);

        Assertions.assertFalse(result);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldAddToNegativeBalanceAtCreditLimit() {
        CreditAccount account = new CreditAccount(
                -5_000,
                5_000,
                15
        );
        boolean result = account.add(1_000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(-4_000, account.getBalance());
    }

    @Test
    public void shouldCalculateInterestForNegativeBalance() {

        CreditAccount account = new CreditAccount(
                -200,
                5_000,
                15);
        int result = account.yearChange();
        int expectedResult = -30;

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnZeroForPositiveBalance() {

        CreditAccount account = new CreditAccount(
                200,
                5_000,
                15);
        int result = account.yearChange();
        int expectedResult = 0;

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnZeroForZeroBalance() {

        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15);
        int result = account.yearChange();
        int expectedResult = 0;

        Assertions.assertEquals(expectedResult, result);
    }

}
