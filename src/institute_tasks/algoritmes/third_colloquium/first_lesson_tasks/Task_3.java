package institute_tasks.algoritmes.third_colloquium.first_lesson_tasks;

import java.util.ArrayList;
import java.util.Scanner;

//В списке из 20 целых чисел найти наибольший элемент и поменять его
//местами с первым элементом
public class Task_3 {
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

        // Находим индекс наибольшего элемента
        int maxIndex = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > list.get(maxIndex)) {
                maxIndex = i;
            }
        }

        // Меняем местами наибольший элемент и первый элемент
        int temp = list.get(0);
        list.set(0, list.get(maxIndex));
        list.set(maxIndex, temp);

        // Выводим результат
        System.out.println("Список после замены наибольшего элемента с первым:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Элемент " + (i + 1) + ": " + list.get(i));
        }

        scanner.close();
    }
}

