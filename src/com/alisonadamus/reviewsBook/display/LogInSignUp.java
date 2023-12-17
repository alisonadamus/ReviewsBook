package com.alisonadamus.reviewsBook.display;

import com.alisonadamus.reviewsBook.functional.FileManager;
import com.alisonadamus.reviewsBook.instances.User;
import java.security.NoSuchAlgorithmException;

/**
 * Клас, що відповідає за реєстрацію та авторизацію користувача.
 */
public class LogInSignUp {

    /**
     * Шлях до файлу для зберігання даних користувача
     */
    static String pathUsers = "src/com/alisonadamus/reviewsBook/files/users.txt";
    /**
     * Масив інформації про користувачів
     */
    static String[] users;
    /**
     * Масив логіну та паролю кожного користувача
     */
    static String[] userData;
    /**
     * Наявність користувача з вказаним логіном
     */
    static boolean loginExist = false;

    /**
     * Реєстрація нового користувача.
     *
     * @param login    Логін нового користувача.
     * @param password Пароль нового користувача.
     */
    public static void signUp(String login, String password) {
        //Отримання даних користувачів з файлу
        users = FileManager.readedFromFile(pathUsers).split("\n");

        for (String user : users) {
            //Отримання масиву логіну[0] та паролю[1] кожного користувача
            userData = user.split("\t");

            //Перевірка наявності користувача з вказаним логіном
            if (userData[0].equals(login)) {
                loginExist = true;
                System.out.println("\u001B[31mКористувача з таким логіном уже існує\u001B[0m");
                break;
            }
        }
        //Перевірка відсутності користувача з вказаним логіном
        if (!loginExist) {
            //Створення об'єкту користувач
            User newUser = new User(login, password);
            String writeUser = newUser.getLogin() + "\t" + newUser.getPassword() + "\n";

            //Запис даних користувача до файлу
            FileManager.writeToFile(writeUser, pathUsers);
            System.out.println("\u001B[32mУспішна реєстрація!\u001B[0m");
        }
        Display.enterMenu();
    }

    /**
     * Авторизація користувача.
     *
     * @param login    Логін користувача для авторизації.
     * @param password Пароль користувача для авторизації.
     * @throws NoSuchAlgorithmException - якщо алгоритм хешування не підтримується,
     * @see User#hashPassword(String password)
     */
    public static void logIn(String login, String password) throws NoSuchAlgorithmException {
        //Отримання даних користувачів з файлу
        users = FileManager.readedFromFile(pathUsers).split("\n");

        for (String user : users) {
            //Отримання масиву логіну[0] та паролю[1] кожного користувача
            userData = user.split("\t");

            //Перевірка наявності користувача з вказаним логіном
            if (userData[0].equals(login)) {
                loginExist = true;

                //Перевірка чи співпадає пароль з файлу з вказаним хеш-паролем
                if (User.hashPassword(password).equals(userData[1])) {
                    System.out.println("\u001B[32mУспішна авторизація!\u001B[0m");

                    //Створення об'єкту користувач
                    User newUser = new User(login, password);
                    Display.reviewsMenu(newUser);
                    break;
                } else {
                    System.out.println("\u001B[31mНевірний пароль\u001B[0m");
                    Display.enterMenu();
                    break;
                }
            }
        }
        //Перевірка відсутності користувача з вказаним логіном
        if (!loginExist) {
            System.out.println("\u001B[31mКористувача з таким логіном не існує\u001B[0m");
            Display.enterMenu();
        }
    }
}
