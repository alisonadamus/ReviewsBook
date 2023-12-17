package com.alisonadamus.reviewsBook.instances;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Клас, що представляє відгук.
 */
public class Review implements Serializable {

    /**
     * Логін користувача, який залишив відгук.
     */
    public String user;
    /**
     * Тема відгуку.
     */
    public String theme;
    /**
     * Опис відгуку.
     */
    public String description;
    /**
     * Дата публікації відгуку.
     */
    public LocalDate publishData;

    /**
     * Конструктор класу Review.
     *
     * @param user        Користувач, що залишив відгук.
     * @param theme       Тема відгуку.
     * @param description Опис відгуку.
     */
    public Review(User user, String theme, String description) {
        this.user = user.getLogin();
        this.theme = theme;
        this.description = description;
        this.publishData = LocalDate.now();
    }

    /**
     * Перевизначений метод toString() для зручного виводу об'єкта у рядок.
     *
     * @return Рядок, що представляє відгук.
     */
    @Override
    public String toString() {
        return "Тема: " + theme + "\n" + "User: " + user + "\n" +
            "Відгук: " + description + "\n" + publishData.toString() + "\n" + "\n";
    }
}
