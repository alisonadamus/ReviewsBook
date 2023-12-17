package com.alisonadamus.reviewsBook.instances;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Клас, що представляє користувача.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Логін користувача.
     */
    private String login;
    /**
     * Пароль користувача.
     */
    private String password;

    /**
     * Конструктор класу User - створення нового об'єкта з певним значенням.
     *
     * @param login    Логін користувача.
     * @param password Пароль користувача, який хешується перед зберіганням.
     */
    public User(String login, String password) {
        this.login = login;
        try {
            this.password = hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Отримання логіну користувача.
     *
     * @return Логін користувача.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Отримання хеш-пароля користувача.
     *
     * @return хеш-пароль користувача.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Отримання хеш-паролю для переданого пароля.
     *
     * @param password Пароль для хешування.
     * @return Хеш пароля.
     * @throws NoSuchAlgorithmException - якщо алгоритм хешування не підтримується.
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        byte[] hashedBytes = messageDigest.digest(password.getBytes());

        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashedBytes) {
            stringBuilder.append(String.format("%02x", b));
        }

        return stringBuilder.toString();
    }
}
