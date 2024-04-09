package institute_tasks.proggraming_language.second_colloquium.course_task;

import java.util.Random;

//Напишите класс, конструктор которого принимает два массива: массив значений и массив весов значений.
//Класс должен содержать метод, который будет возвращать элемент из первого массива случайным образом, с учётом его веса.
  //      Пример:
//Дан массив [1, 2, 3], и массив весов [1, 2, 10].
  //      В среднем, значение «1» должно возвращаться в 2 раза реже, чем значение «2» и в десять раз реже, чем значение «3».
//напиши код на java
import java.util.Random;

public class WeightedRandomSelector {
    private int[] values;  // Массив значений
    private int[] weights;  // Массив весов значений
    private Random random;  // Генератор случайных чисел

    // Конструктор класса, принимает массив значений и массив весов значений
    public WeightedRandomSelector(int[] values, int[] weights) {
        // Проверка, что массивы не пусты и одинаковой длины
        if (values == null || weights == null || values.length == 0 || weights.length == 0 || values.length != weights.length) {
            throw new IllegalArgumentException("Values and weights arrays must not be null or empty, and must have the same length");
        }

        this.values = values;
        this.weights = weights;
        this.random = new Random();
    }

    // Метод для выбора случайного элемента с учётом его веса
    public int selectRandomWithWeight() {
        // Вычисляем сумму всех весов
        int totalWeight = 0;
        for (int weight : weights) {
            totalWeight += weight;
        }

        // Генерируем случайное число в диапазоне от 0 до суммы всех весов
        int randomNumber = random.nextInt(totalWeight);

        // Находим элемент, соответствующий случайному числу
        int cumulativeWeight = 0;
        for (int i = 0; i < values.length; i++) {
            cumulativeWeight += weights[i];
            if (randomNumber < cumulativeWeight) {
                return values[i];
            }
        }

        // Возвращаем последний элемент, если случайное число вышло за пределы диапазона
        return values[values.length - 1];
    }

    public static void main(String[] args) {
        // Пример использования класса
        int[] values = {1, 4, 3}; // Значения
        int[] weights = {1, 12, 10}; // Веса
        WeightedRandomSelector selector = new WeightedRandomSelector(values, weights);

        // Выбираем случайный элемент с учётом его веса
        int selectedValue = selector.selectRandomWithWeight();
        System.out.println("Selected value: " + selectedValue);
    }
}
