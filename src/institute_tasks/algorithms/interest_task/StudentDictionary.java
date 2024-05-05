package institute_tasks.algorithms.interest_task;

import java.io.*;
import java.util.*;

import static institute_tasks.algorithms.interest_task.AllProgramm.createTransliterationMap;

public class StudentDictionary {
    private static final Map<String, Student> studentMap = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить студента");
            System.out.println("2. Найти студента по имени");
            System.out.println("3. Вывести среднюю оценку студента");
            System.out.println("4. Перевести имя на русский");
            System.out.println("5. Перевести имя на латиницу");
            System.out.println("6. Вывести средние оценки всех студентов");
            System.out.println("7. Сортировать студентов по рейтингу (по возрастанию)");
            System.out.println("8. Искать данные из файла");
            System.out.println("9. Сортировать студентов по рейтингу (по убыванию)");
            System.out.println("10. Выйти из программы");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addStudent();
                    saveDataToFile(); // Сохраняем данные после добавления студента
                    break;
                case 2:
                    findStudent();
                    break;
                case 3:
                    calculateAverageGrade();
                    break;
                case 4:
                    translateToRussian();
                    break;
                case 5:
                    translateToLatin();
                    break;
                case 6:
                    printAllAverageGrades();
                    break;
                case 7:
                    sortStudentsByUnRating();
                    break;
                case 8:
                    searchFromFile();
                    break;
                case 9:
                    sortStudentsByRating();
                    break;
                case 10:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
        scanner.close();
    }

    private static void addStudent() {
        System.out.print("Введите имя студента: ");
        String name = scanner.next();
        System.out.print("Введите фамилию студента: ");
        String surname = scanner.next();
        System.out.print("Введите номер телефона студента: ");
        String phoneNumber = scanner.next();
        System.out.print("Введите количество оценок студента: ");
        int numGrades = scanner.nextInt();

        List<Integer> grades = new ArrayList<>();
        System.out.println("Введите оценки студента:");
        for (int i = 0; i < numGrades; i++) {
            System.out.print("Оценка " + (i + 1) + ": ");
            int grade = scanner.nextInt();
            grades.add(grade);
        }

        Student student = new Student(name, surname, phoneNumber, grades);
        studentMap.put(name, student);
        student.setRating(student.calculateAverageGrade());
        System.out.println("Студент добавлен.");
    }

    private static void findStudent() {
        System.out.print("Введите имя студента для поиска: ");
        String name = scanner.next();
        Student student = studentMap.get(name);
        if (student != null) {
            System.out.println("Информация о студенте:");
            System.out.println(student);
        } else {
            System.out.println("Студент не найден.");
        }
    }

    private static void calculateAverageGrade() {
        System.out.print("Введите имя студента для вычисления средней оценки: ");
        String name = scanner.next();
        Student student = studentMap.get(name);
        if (student != null) {
            double average = student.calculateAverageGrade();
            System.out.println("Средняя оценка студента " + translateToCyrillic(name) + ": " + average);
        } else {
            System.out.println("Студент не найден.");
        }
    }

    private static void translateToRussian() {
        System.out.print("Введите имя на латинице для перевода на русский: ");
        String latinName = scanner.next();
        String russianName = translateToCyrillic(latinName);
        System.out.println("Имя на русском: " + russianName);
    }

    private static void translateToLatin() {
        System.out.print("Введите имя на русском для перевода на латиницу: ");
        String russianName = scanner.next();
        String latinName = translateToLatin(russianName);
        System.out.println("Имя на латинице: " + latinName);
    }

    private static String translateToCyrillic(String latinText) {
        Map<String, String> transliterationMap = createTransliterationMap();
        StringBuilder cyrillicText = new StringBuilder();
        for (int i = 0; i < latinText.length(); i++) {
            char c = latinText.charAt(i);
            String letter = String.valueOf(c).toLowerCase();
            if (transliterationMap.containsKey(letter)) {
                cyrillicText.append(transliterationMap.get(letter));
            } else {
                cyrillicText.append(c);
            }
        }
        return cyrillicText.toString();
    }

    private static String translateToLatin(String russianText) {
        Map<String, String> transliterationMap = createTransliterationMap();
        StringBuilder latinText = new StringBuilder();
        for (int i = 0; i < russianText.length(); i++) {
            char c = russianText.charAt(i);
            String letter = String.valueOf(c);
            if (transliterationMap.containsValue(letter)) {
                for (Map.Entry<String, String> entry : transliterationMap.entrySet()) {
                    if (entry.getValue().equals(letter)) {
                        latinText.append(entry.getKey());
                        break;
                    }
                }
            } else {
                latinText.append(c);
            }
        }
        return latinText.toString();
    }

    private static void printAllAverageGrades() {
        if (studentMap.isEmpty()) {
            System.out.println("Словарь студентов пуст.");
            return;
        }

        System.out.println("Средние оценки всех студентов:");
        for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
            String name = entry.getKey();
            Student student = entry.getValue();
            double average = student.calculateAverageGrade();
            System.out.println("Студент: " + translateToCyrillic(name) + ", Средняя оценка: " + average);
        }
    }

    private static void saveDataToFile() {
        try {
            PrintWriter writer = new PrintWriter("student_data.txt");
            for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
                writer.println("Студент: " + entry.getKey());
                writer.println(entry.getValue());
                writer.println();
            }
            writer.close();
            System.out.println("Данные сохранены в файл 'student_data.txt'.");
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при сохранении данных в файл: " + e.getMessage());
        }
    }

    private static void searchFromFile() {
        System.out.print("Введите имя студента для поиска: ");
        String searchName = scanner.next();
        searchDataFromFile(searchName);
    }

    private static void searchDataFromFile(String searchName) {
        // Реализация поиска данных из файла
    }
    private static void sortStudentsByUnRating() {
        List<Student> students = new ArrayList<>(studentMap.values());
        Collections.sort(students, Comparator.comparingDouble(Student::getRating));
        System.out.println("Студенты отсортированы по убывающемум рейтингу:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void sortStudentsByRating() {
        List<Student> students = new ArrayList<>(studentMap.values());
        Collections.sort(students, Comparator.comparingDouble(Student::getRating).reversed());
        System.out.println("Студенты отсортированы по возрастающему рейтингу рейтингу:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static Map<String, String> createTransliterationMap() {
        Map<String, String> transliterationMap = new HashMap<>();
        transliterationMap.put("a", "а");
        transliterationMap.put("b", "б");
        transliterationMap.put("v", "в");
        transliterationMap.put("g", "г");
        transliterationMap.put("d", "д");
        transliterationMap.put("e", "Э");
        transliterationMap.put("yo", "ё");
        transliterationMap.put("zh", "ж");
        transliterationMap.put("z", "з");
        transliterationMap.put("i", "и");
        transliterationMap.put("y", "й");
        transliterationMap.put("k", "к");
        transliterationMap.put("l", "л");
        transliterationMap.put("m", "м");
        transliterationMap.put("n", "н");
        transliterationMap.put("o", "о");
        transliterationMap.put("p", "п");
        transliterationMap.put("r", "р");
        transliterationMap.put("s", "C");
        transliterationMap.put("t", "т");
        transliterationMap.put("u", "у");
        transliterationMap.put("f", "ф");
        transliterationMap.put("kh", "х");
        transliterationMap.put("tc", "ц");
        transliterationMap.put("ch", "ч");
        transliterationMap.put("sh", "ш");
        transliterationMap.put("shch", "щ");
        transliterationMap.put("y", "ы");
        transliterationMap.put("iu", "ю");
        transliterationMap.put("ia", "я");
        return transliterationMap;
    }
}

class Student {
    private String name;
    private String surname;
    private String phoneNumber;
    private List<Integer> grades;
    private double rating;

    public Student(String name, String surname, String phoneNumber, List<Integer> grades) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.grades = grades;
    }

    public double calculateAverageGrade() {
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Имя: " + translateToCyrillic(name) +
                ", Фамилия: " + translateToCyrillic(surname) +
                ", Номер телефона: " + phoneNumber +
                ", Оценки: " + grades +
                ", Рейтинг: " + rating;
    }

    private static String translateToCyrillic(String latinText) {
        Map<Character, String> transliterationMap = createTransliterationMap();
        StringBuilder cyrillicText = new StringBuilder();
        for (int i = 0; i < latinText.length(); i++) {
            char c = latinText.charAt(i);
            String letter = String.valueOf(c).toLowerCase();
            if (transliterationMap.containsKey(letter)) {
                cyrillicText.append(transliterationMap.get(letter));
            } else {
                cyrillicText.append(c);
            }
        }
        return cyrillicText.toString();
    }
}
