package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyTestCase1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCard = DataHelper.getCard1();
        var startingBalance1 = dashboardPage.getCardBalance(firstCard);
        var secondCard = DataHelper.getCard2();
        var startingBalance2 = dashboardPage.getCardBalance(secondCard);
        var amount = DataHelper.getRandomAmount(startingBalance1);
        var moneyTransferPage = dashboardPage.cardToTransfer(secondCard);
        moneyTransferPage.makeValidTransfer(firstCard, String.valueOf(amount));
        var actualBalance1 = dashboardPage.getCardBalance(firstCard);
        var actualBalance2 = dashboardPage.getCardBalance(secondCard);
        Assertions.assertEquals(startingBalance1 - amount, actualBalance1);
        Assertions.assertEquals(startingBalance2 + amount, actualBalance2);
    }

    @Test
    void shouldTransferMoneyTestCase2() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCard = DataHelper.getCard1();
        var startingBalance1 = dashboardPage.getCardBalance(firstCard);
        var secondCard = DataHelper.getCard2();
        var startingBalance2 = dashboardPage.getCardBalance(secondCard);
        var amount = DataHelper.getRandomAmount(startingBalance1);
        var moneyTransferPage = dashboardPage.cardToTransfer(firstCard);
        moneyTransferPage.makeValidTransfer(secondCard, String.valueOf(amount));
        var actualBalance1 = dashboardPage.getCardBalance(firstCard);
        var actualBalance2 = dashboardPage.getCardBalance(secondCard);
        Assertions.assertEquals(startingBalance1 + amount, actualBalance1);
        Assertions.assertEquals(startingBalance2 - amount, actualBalance2);
    }

    @Test
    void shouldNotTransferMoneyTestCase1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCard = DataHelper.getCard1();
        var startingBalance1 = dashboardPage.getCardBalance(firstCard);
        var secondCard = DataHelper.getCard2();
        var startingBalance2 = dashboardPage.getCardBalance(secondCard);
        var amount = DataHelper.getRandomImpossibleAmount(startingBalance1);
        var moneyTransferPage = dashboardPage.cardToTransfer(secondCard);
        moneyTransferPage.transferMoney(firstCard, String.valueOf(amount));
        moneyTransferPage.depositError("Ошибка!");
        var actualBalance1 = dashboardPage.getCardBalance(firstCard);
        var actualBalance2 = dashboardPage.getCardBalance(secondCard);
        Assertions.assertEquals(startingBalance1, actualBalance1);
        Assertions.assertEquals(startingBalance2, actualBalance2);
    }

    @Test
    void shouldNotTransferMoneyTestCase2() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCard = DataHelper.getCard1();
        var startingBalance1 = dashboardPage.getCardBalance(firstCard);
        var secondCard = DataHelper.getCard2();
        var startingBalance2 = dashboardPage.getCardBalance(secondCard);
        var amount = DataHelper.getRandomImpossibleAmount(startingBalance2);
        var moneyTransferPage = dashboardPage.cardToTransfer(firstCard);
        moneyTransferPage.transferMoney(secondCard, String.valueOf(amount));
        moneyTransferPage.depositError("Ошибка!");
        var actualBalance1 = dashboardPage.getCardBalance(firstCard);
        var actualBalance2 = dashboardPage.getCardBalance(secondCard);
        Assertions.assertEquals(startingBalance1, actualBalance1);
        Assertions.assertEquals(startingBalance2, actualBalance2);
    }
}
