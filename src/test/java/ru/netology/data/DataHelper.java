package ru.netology.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {
    private DataHelper() {

    }
    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardsInfo {
        private String number;
    }

    public static CardsInfo getCard1() {
        return new CardsInfo("5559000000000001");
    }

    public static CardsInfo getCard2() {
        return new CardsInfo("5559000000000002");
    }

    public static int getRandomAmount(int balance) {

        return new Random().nextInt(balance) + 1;
    }

    public static int getRandomImpossibleAmount(int balance) {

        return Math.abs(balance) + new Random().nextInt(1000);
    }

}
