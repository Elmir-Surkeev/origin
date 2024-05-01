package institute_tasks.algoritmes.third_colloquium.second_lesson_tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


//Составьте словарь о студентах.
//В первой строчке записано целое число – количество студентов. В
//следующих N строчках записана информация о студентах. Каждая
//строчка состоит из трёх частей, разделённых пробелом – фамилии
//студента, названия специальности и номере группы.
//В следующей строке записан запрос — это название специальности.
//Выведите через запятую фамилии всех студентов, обучающихся на этой
//специальности. Если таких фамилий нет, выведите фразу «Проверьте
//запрос».
public class StudentDictionary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество студентов: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // считываем лишний перевод строки

        Map<String, String> students = new HashMap<>();

        System.out.println("Введите информацию о студентах:");
        for (int i = 0; i < n; i++) {
            System.out.print("Фамилия студента " + (i + 1) + ": ");
            String lastName = scanner.next();
            System.out.print("Специальность: ");
            String specialty = scanner.next();
            System.out.print("Номер группы: ");
            String group = scanner.next();
            students.put(specialty, students.getOrDefault(specialty, "") + lastName + ",");
        }

        System.out.print("Введите название специальности для поиска студентов: ");
        String query = scanner.next();

        String studentsBySpecialty = students.getOrDefault(query, "");
        if (!studentsBySpecialty.isEmpty()) {
            System.out.println("Фамилии студентов на специальности " + query + ": " + studentsBySpecialty);
        } else {
            System.out.println("Проверьте запрос");
        }

        scanner.close();
    }
}
