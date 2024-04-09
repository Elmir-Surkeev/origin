package institute_tasks.proggraming_language.second_colloquium.course_task.test_scanner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class PoleDream {
    public static void main(String[] args) {
        Category[] categories = new Category[] {
                new Category("животные",
                        new String[] { "Лев", "Слон", "Обезьяна", "Тигр", "Пингвин", "Заяц", "Волк", "Жираф",
                                "Крокодил", "Попугай" }),
                new Category("фрукты",
                        new String[] { "Яблоко", "Банан", "Апельсин", "Груша", "Виноград", "Киви", "Манго", "Ананас",
                                "Персик", "Клубника" }),
                new Category("овощи",
                        new String[] { "Помидор", "Огурец", "Морковь", "Картофель", "Брокколи", "Лук", "Шпинат",
                                "Капуста", "Чеснок", "Перец" }),
                new Category("бытовые предметы",
                        new String[] { "Чайник", "Телевизор", "Микроволновка", "Пылесос", "Утюг", "Миксер", "Фен",
                                "Мебель", "Посуда", "Лампа" }),
                new Category("боги олимпа",
                        new String[] { "Зевс", "Гера", "Посейдон", "Деметра", "Афина", "Аполлон", "Артемида", "Гермес",
                                "Афродита", "Гепест" }),
                new Category("цветок",
                        new String[] { "Роза", "Тюльпан", "Лилия", "Хризантема", "Орхидея", "Пион", "Астра", "Гвоздика",
                                "Мак", "Фиалка" }),
        };

        Random random = new Random();
        char letter = ' ';
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в поле чудес, у нас есть следующие категории\n...");

        for (Category category : categories) {
            System.err.println(category.getName());
        }

        do {
            int randomCategoryIndex = random.nextInt(categories.length);
            Category choosedCategory = categories[randomCategoryIndex];
            int randomWordIndex = random.nextInt(choosedCategory.getWords().size());
            String choosedWord = choosedCategory.getWord(randomWordIndex);
            int lives = Math.round(choosedWord.length() / 2);

            System.out.printf("...\nВыбрана категория: %s\n", choosedCategory.getName());

            StringBuffer cipher = new StringBuffer(placeholder(choosedWord));

            while (lives != 0 && !choosedWord.equalsIgnoreCase(cipher.toString())) {
                System.out.printf("Ваше слово: %s\n", cipher);
                System.out.printf("Количество ваших жизней: %d\n", lives);
                System.out.println("Введите букву или '!' для выхода: ");

                letter = Character.toLowerCase(scanner.next().charAt(0));
                if (letter == '!') {
                    break;
                }

                boolean found = false;
                for (int i = 0; i < choosedWord.length(); i++) {
                    if (Character.toLowerCase(choosedWord.charAt(i)) == letter) {
                        cipher.setCharAt(i, choosedWord.charAt(i));
                        found = true;
                    }
                }

                if (!found) {
                    lives--;
                    System.out.println("Неправильно введена буква");
                }
            }
            if (lives == 0) {
                System.out.println("Жизни закончились...");
            } else if (choosedWord.equalsIgnoreCase(cipher.toString())) {
                System.out.printf("Результат: %s\n", cipher);
                System.out.println("Вы молодец! Вы получаете 40 баллов!");
            } else {
                System.out.println("Неправильно введено слово!!");
            }
        } while (letter != '!');
        System.out.println("Выход!!");
        scanner.close();
    }

    public static String placeholder(String array) {
        char[] charArr = array.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            charArr[i] = '*';
        }
        return new String(charArr);
    }
}

class Category {
    private String name;
    private ArrayList<String> words;

    Category(String name, String[] words) {
        this.name = name;
        this.words = new ArrayList<String>();
        this.words.addAll(Arrays.asList(words));
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWord(int index, String word) {
        this.words.set(index, word);
    }

    public String getWord(int index) {
        return this.words.get(index);
    }

    public ArrayList<String> getWords() {
        return words;
    }
}
