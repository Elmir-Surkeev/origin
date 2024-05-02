package institute_tasks.algoritmes.third_colloquium.first_lesson_tasks;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;


//Составить программу, которая считывает сначала количество
//оценок, потом по очереди сами эти оценки, затем выводит их же в том же порядке
//        (используем список). Найдите среднюю оценку за урок.


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

        scanner.close();
    }
}
