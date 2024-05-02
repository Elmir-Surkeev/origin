package institute_tasks.algoritmes.third_colloquium.first_lesson_tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считываем количество оценок
        System.out.print("Введите количество оценок: ");
        int numGrades = scanner.nextInt();

        // Создаем список для хранения оценок
        ArrayList<Integer> grades = new ArrayList<>();

        // Считываем оценки
        System.out.println("Введите оценки:");
        for (int i = 0; i < numGrades; i++) {
            System.out.print("Оценка " + (i + 1) + ": ");
            int grade = scanner.nextInt();
            grades.add(grade); // Добавляем оценку в список
        }

        // Выводим оценки
        System.out.println("Введенные оценки:");
        for (int i = 0; i < numGrades; i++) {
            System.out.println("Оценка " + (i + 1) + ": " + grades.get(i));
        }

        // Находим среднюю оценку
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        double average = sum / numGrades;
        System.out.println("Средняя оценка: " + average);

        // Сохраняем данные в файл
        saveDataToFile(numGrades, grades, average);

        scanner.close();
    }

    private static void saveDataToFile(int numGrades, ArrayList<Integer> grades, double average) {
        try {
            FileWriter writer = new FileWriter("grades.txt");
            writer.write("Количество оценок: " + numGrades + "\n");
            writer.write("Оценки:\n");
            for (int i = 0; i < numGrades; i++) {
                writer.write("Оценка " + (i + 1) + ": " + grades.get(i) + "\n");
            }
            writer.write("Средняя оценка: " + average + "\n");
            writer.close();
            System.out.println("Данные успешно сохранены в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных в файл: " + e.getMessage());
        }
    }
}
