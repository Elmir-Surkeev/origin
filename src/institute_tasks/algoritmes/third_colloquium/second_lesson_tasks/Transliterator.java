package institute_tasks.algoritmes.third_colloquium.second_lesson_tasks;

import java.util.HashMap;
import java.util.Map;

//Дан русский текст. Текст может состоять из любых символов, вам
//необходимо транслитерировать (то есть заменить все русские буквы на английские)
//только русские буквы, а остальные оставить на месте. Строчные буквы заменяются
//        на строчные, заглавные заменяются на заглавные. Если заглавная буква
//превращается при транслитерации в несколько букв, то заглавной должна остаться
//только первая из них (например, «Ц» → «Tc»).
//Правила трансляции:
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Transliterator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите текст на русском:");
        String russianText = scanner.nextLine();

        String transliteratedText = transliterate(russianText);
        System.out.println("Текст после транслитерации:");
        System.out.println(transliteratedText);

        scanner.close();
    }

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
