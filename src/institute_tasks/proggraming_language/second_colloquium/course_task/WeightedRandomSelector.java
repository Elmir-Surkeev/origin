package institute_tasks.proggraming_language.second_colloquium.course_task;

import java.util.Random;

public class WeightedRandomSelector {
    private int[] values;  // Массив значений
    private int[] weights;  // Массив весов значений
    private Random random;  // Генератор случайных чисел

    // Конструктор класса, принимает массив значений и массив весов значений
    public WeightedRandomSelector(int[] values, int[] weights) {
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
        int[] values = {1, 2, 3};
        int[] weights = {1, 2, 10};
        WeightedRandomSelector selector = new WeightedRandomSelector(values, weights);

        // Выбираем случайный элемент с учётом его веса
        int selectedValue = selector.selectRandomWithWeight();
        System.out.println("Selected value: " + selectedValue);
    }
}
