package institute_tasks.algoritmes.first_colloquium;

public class task_1_box {

    // Функция sin(x) - x + 2
    public static double function(double x) {
        return Math.sin(x) - x + 2;
    }
    // Метод квадратичной аппроксимации для поиска корня
    public static double quadraticApproximation(double x0, double epsilon, int N) {
        int i = 0;
        double x1 = x0;
        double x2 = x0 + epsilon; // Второе начальное приближение

        while (i < N) {
            double f0 = function(x0);
            double f1 = function(x1);
            double f2 = function(x2);

            // Вычисляем новое приближение с помощью квадратичной аппроксимации
            double newX = x2 - ((x2 - x1) * (x2 - x1) * (f2 - f0)) / ((x2 - x0) * (f2 - f1) + (x2 - x1) * (f2 - f0));

            if (Math.abs(newX - x2) < epsilon) {
                return newX;
            }

            x0 = x1;
            x1 = x2;
            x2 = newX;

            i++;
        }
        return Double.NaN; // Возвращаем NaN, если не удалось достичь точности за N итераций
    }
    public static void main(String[] args) {
        double x0 = 2.55;
        double epsilon = 1e-6;
        int N = 1000;

        double root = quadraticApproximation(x0, epsilon, N);

        if (!Double.isNaN(root)) {
            System.out.println("Найденное значение корня: " + root);
            System.out.println("Значение функции в корне: " + function(root));
        } else {
            System.out.println("Точность не достигнута за " + N + " шагов.");
        }
    }
}

