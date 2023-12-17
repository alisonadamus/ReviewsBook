package com.alisonadamus.reviewsBook.display;

import com.alisonadamus.reviewsBook.functional.ReviewManager;
import com.alisonadamus.reviewsBook.instances.User;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Клас, що відповідає за відображення повідемлень користувачу
 */
public class Display {

    /**
     * Об'єкт для зчитування введених користувачем даних
     */
    static Scanner scanner = new Scanner(System.in);
    /**
     * Введений користувачем логін
     */
    static String login;
    /**
     * Введений користувачем пароль
     */
    static String password;

    /**
     * Відображення меню входу. Користувач може обрати авторизацію чи реєстрацію.
     */
    public static void enterMenu() {
        System.out.println("Книга відгуків кафе *Тірамісу*");
        System.out.println("[1] Авторизуватися");
        System.out.println("[2] Зареєструватися");

        switch (scanner.nextLine()) {
            case "1":
                logInMenu();
                break;
            case "2":
                signUpMenu();
                break;
            default:
                System.out.println("\u001B[31mНатисніть відповідну кнопку\u001B[0m");
                enterMenu();
        }

    }

    /**
     * Відображення меню авторизації.
     */
    public static void logInMenu() {
        System.out.println("Авторизація");
        System.out.println("Введіть логін:");
        login = scanner.nextLine();
        System.out.println("Введіть пароль:");
        password = scanner.nextLine();
        try {
            LogInSignUp.logIn(login, password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Відображення меню реєстрації.
     */
    public static void signUpMenu() {
        System.out.println("Реєстрація");
        System.out.println("Введіть логін:");
        login = scanner.nextLine();
        System.out.println("Введіть пароль:");
        password = scanner.nextLine();
        LogInSignUp.signUp(login, password);
    }

    /**
     * Відображення головного меню. Користувач може обрати написати відгук, переглянути всі відгуки
     * та вийти з аккаунту.
     *
     * @param user авторизований користувач.
     */
    public static void reviewsMenu(User user) {
        System.out.println("Книга відгуків кафе *Тірамісу*");
        System.out.println("[1] Написати відгук");
        System.out.println("[2] Переглянути відгуки");
        System.out.println("[3] Вийти з аккаунту");

        switch (scanner.nextLine()) {
            case "1":
                writeReview(user);
                break;
            case "2":
                readReviews(user);
                break;
            case "3":
                enterMenu();
                break;
            default:
                System.out.println("\u001B[31mНатисніть відповідну кнопку\u001B[0m");
                reviewsMenu(user);
        }
    }

    /**
     * Відображення меню написання відгуку. Користувач повинен обрати тему, та написати описати свій
     * відгук.
     *
     * @param user авторизований користувач.
     */
    public static void writeReview(User user) {
        String theme = " ";

        System.out.println("Оберіть тему відгуку");
        System.out.println("[1] Обслуговування");
        System.out.println("[2] Страви та напої");
        System.out.println("[3] Графік роботи");
        System.out.println("[4] Ціни");
        System.out.println("[5] Інше");

        switch (scanner.nextLine()) {
            case "1":
                theme = "Обслуговування";
                break;
            case "2":
                theme = "Страви та напої";
                break;
            case "3":
                theme = "Графік роботи";
                break;
            case "4":
                theme = "Ціни";
                break;
            case "5":
                theme = "Інше";
                break;
            default:
                System.out.println("\u001B[31mНатисніть відповідну кнопку\u001B[0m");
                writeReview(user);
        }

        System.out.println("Напишіть свій відгук:");
        String review = scanner.nextLine();

        ReviewManager.writeReview(user, theme, review);
        System.out.println("\u001B[32mВаш відгук збережено!\u001B[0m");

        reviewsMenu(user);
    }

    /**
     * Відображення всіх відгуку.
     *
     * @param user авторизований користувач.
     */
    public static void readReviews(User user) {
        System.out.println(ReviewManager.allReviews());
        reviewsMenu(user);
    }
}
