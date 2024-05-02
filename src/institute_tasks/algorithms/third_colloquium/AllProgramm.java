package institute_tasks.algorithms.third_colloquium;

import java.util.*;

public class AllProgramm {
    private static final Map<String, Student> studentMap = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить студента");
            System.out.println("2. Найти студента по имени");
            System.out.println("3. Вывести среднюю оценку студента");
            System.out.println("4. Посмотреть имя на латинице");
            System.out.println("5. Выйти из программы");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    findStudent();
                    break;
                case 3:
                    calculateAverageGrade();
                    break;
                case 4:
                    showLatinizedName();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
        scanner.close();
    }

    // Добавление студента
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
        System.out.println("Студент добавлен.");
    }

    // Поиск студента по имени
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

    // Вычисление средней оценки студента
    private static void calculateAverageGrade() {
        System.out.print("Введите имя студента для вычисления средней оценки: ");
        String name = scanner.next();
        Student student = studentMap.get(name);
        if (student != null) {
            double average = student.calculateAverageGrade();
            System.out.println("Средняя оценка студента " + name + ": " + average);
        } else {
            System.out.println("Студент не найден.");
        }
    }

    // Просмотр имени на латинице
    private static void showLatinizedName() {
        System.out.print("Введите имя на русском для просмотра на латинице: ");
        String russianName = scanner.next();
        String latinizedName = transliterate(russianName);
        System.out.println("Имя на латинице: " + latinizedName);
    }

    // Транслитерация имени на латиницу
    public static String transliterate(String russianText) {
        Map<Character, String> transliterationMap = createTransliterationMap();
        StringBuilder transliteratedText = new StringBuilder();

        for (char c : russianText.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char lowercase = Character.toLowerCase(c);
                if (transliterationMap.containsKey(lowercase)) {
                    String transliteratedLetter = transliterationMap.get(lowercase);
                    transliteratedText.append(transliteratedLetter.substring(0, 1).toUpperCase());
                } else {
                    transliteratedText.append(c);
                }
            } else if (Character.isLowerCase(c)) {
                if (transliterationMap.containsKey(c)) {
                    transliteratedText.append(transliterationMap.get(c));
                } else {
                    transliteratedText.append(c);
                }
            } else {
                transliteratedText.append(c);
            }
        }

        return transliteratedText.toString();
    }

    // Создание карты транслитерации
    public static Map<Character, String> createTransliterationMap() {
        Map<Character, String> transliterationMap = new HashMap<>();
        transliterationMap.put('а', "a");
        transliterationMap.put('б', "b");
        transliterationMap.put('в', "v");
        transliterationMap.put('г', "g");
        transliterationMap.put('д', "d");
        transliterationMap.put('е', "e");
        transliterationMap.put('ё', "e");
        transliterationMap.put('ж', "zh");
        transliterationMap.put('з', "z");
        transliterationMap.put('и', "i");
        transliterationMap.put('й', "i");
        transliterationMap.put('к', "k");
        transliterationMap.put('л', "l");
        transliterationMap.put('м', "m");
        transliterationMap.put('н', "n");
        transliterationMap.put('о', "o");
        transliterationMap.put('п', "p");
        transliterationMap.put('р', "r");
        transliterationMap.put('с', "s");
        transliterationMap.put('т', "t");
        transliterationMap.put('у', "u");
        transliterationMap.put('ф', "f");
        transliterationMap.put('х', "kh");
        transliterationMap.put('ц', "tc");
        transliterationMap.put('ч', "ch");
        transliterationMap.put('ш', "sh");
        transliterationMap.put('щ', "shch");
        transliterationMap.put('ы', "y");
        transliterationMap.put('ъ', "");
        transliterationMap.put('ь', "");
        transliterationMap.put('э', "e");
        transliterationMap.put('ю', "iu");
        transliterationMap.put('я', "ia");
        return transliterationMap;
    }
}
