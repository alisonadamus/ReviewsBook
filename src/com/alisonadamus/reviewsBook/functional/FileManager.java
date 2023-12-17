package com.alisonadamus.reviewsBook.functional;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Клас, що відповідає за роботу з файлами.
 */
public class FileManager {

    /**
     * Шлях до каталогу для зберігання файлів
     */
    static String pathFiles = "src/com/alisonadamus/reviewsBook/files";

    /**
     * Перевіряє існування файлу за заданим шляхом.
     *
     * @param file файл, існування якого перевіряється.
     * @return true, якщо файл існує; false, якщо не існує.
     */
    public static boolean isFileExists(File file) {
        return file.isFile();
    }

    /**
     * Створює файл за заданим шляхом, якщо він не існує.
     *
     * @param pathToFile шлях до файлу, який потрібно створити.
     */
    public static void createFile(String pathToFile) {
        if (!isFileExists(new File(pathToFile))) {
            try {
                File directory = new File(pathFiles);
                directory.mkdir();
                FileWriter fileWriter = new FileWriter(pathToFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Записує вказаний рядок у файл за заданим шляхом.
     *
     * @param writeToFile Рядок, який потрібно записати у файл.
     * @param pathToFile  Шлях до файлу, куди буде записуватись рядок.
     */
    public static void writeToFile(String writeToFile, String pathToFile) {
        createFile(pathToFile);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathToFile, true);
            // Запис рядка у файл у вигляді байтів
            fileOutputStream.write(writeToFile.getBytes());

            // Закриття потоку
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Виконує читання файлу за заданим шляхом.
     *
     * @param pathToFile Шлях до файлу, який буде зчитано.
     * @return Рядок, який містить вміст файлу.
     */
    public static String readedFromFile(String pathToFile) {
        createFile(pathToFile);

        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(pathToFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            InputStreamReader reader = new InputStreamReader(bufferedInputStream,
                StandardCharsets.UTF_8);

            // Зчитування символів з файлу
            int content;
            while ((content = reader.read()) != -1) {
                stringBuilder.append((char) content);
            }

            // Закриття потоків
            fileInputStream.close();
            bufferedInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
