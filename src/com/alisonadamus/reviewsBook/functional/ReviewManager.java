package com.alisonadamus.reviewsBook.functional;

import com.alisonadamus.reviewsBook.instances.Review;
import com.alisonadamus.reviewsBook.instances.User;

/**
 * Клас, що відповідає за перенапралення відгуків.
 */
public class ReviewManager {
    /**
     * Шлях до файлу для зберігання відгуків
     */
    static String pathReviews = "src/com/alisonadamus/reviewsBook/files/reviews.txt";

    /**
     * Створює відгук з отриманих даних та записує до файлу.
     *
     * @param user  користувач, що написав відгук.
     * @param theme тема відгуку.
     * @param description   текст відгуку.
     */
    public static void writeReview(User user, String theme, String description){
        Review newReview = new Review(user, theme, description);
        FileManager.writeToFile(newReview.toString(), pathReviews);
    }

    /**
     * Отримує всі відгуки з файлу.
     *
     * @return Рядок всіх відгуків.
     */
    public static String allReviews(){
        return FileManager.readedFromFile(pathReviews);
    }

}
