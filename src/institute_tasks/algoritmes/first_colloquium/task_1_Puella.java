package institute_tasks.algoritmes.first_colloquium;

public class task_1_Puella {

    // Функция sin(x) - x + 2
    public static double function(double x) {
        return Math.sin(x) - x + 2;
    }

    // Аппроксимация синуса
    public static double sinApproximation(double x) {
        // Ряд Маклорена для синуса
        double result = x;
        double term = x;
        for (int i = 3; Math.abs(term) > 1e-6; i += 2) {
            term *= -x * x / (i * (i - 1));
            result += term;
        }
        return result;
    }

    // Метод Пуэлла для поиска корня
    public static double puellMethod(double x0, double epsilon, int N) {
        int i = 0;
        double x1 = x0;
        double delta = epsilon / 10; // Параметр для вычисления производных

        while (i < N) {
            double f0 = function(x1);
            double f1 = function(x1 + delta);
            double f2 = function(x1 - delta);
            double derivative = (f1 - f2) / (2 * delta);

            x1 = x1 - (2 * epsilon * f0) / derivative;

            if (Math.abs(x1 - x0) < epsilon) {
                return x1;
            }
            x0 = x1;
            i++;
        }
        return Double.NaN; // Возвращаем NaN, если не удалось достичь точности за N итераций
    }

    public static void main(String[] args) {
        double x0 = 2.55;
        double epsilon = 1e-6;
        int N = 1000;

        double root = puellMethod(x0, epsilon, N);

        if (!Double.isNaN(root)) {
            System.out.println("Найденное значение корня: " + root);
            System.out.println("Значение функции в корне: " + function(root));
        } else {
            System.out.println("Точность не достигнута за " + N + " шагов.");
        }
    }
}
