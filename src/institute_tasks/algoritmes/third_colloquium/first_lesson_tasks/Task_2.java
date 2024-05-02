package institute_tasks.algoritmes.third_colloquium.first_lesson_tasks;

import java.util.ArrayList;
import java.util.Scanner;

//Для списка А, состоящего из 20 элементов, вычислить количество
//отрицательных элементов списка
public class Task_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Создаем список для хранения элементов
        ArrayList<Integer> list = new ArrayList<>();

        // Заполняем список
        System.out.println("Введите 5 целых чисел:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            int element = scanner.nextInt();
            list.add(element);
        }

        // Подсчитываем количество отрицательных элементов
        int negativeCount = 0;
        for (int num : list) {
            if (num < 0) {
                negativeCount++;
            }
        }

        // Выводим результат
        System.out.println("Количество отрицательных элементов: " + negativeCount);

        scanner.close();
    }
}
