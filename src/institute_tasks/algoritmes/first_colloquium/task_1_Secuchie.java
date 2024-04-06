package institute_tasks.algoritmes.first_colloquium;

public class task_1_Secuchie{
    // Функция sin(x) - x + 2
    public static double function(double x) {
        return Math.sin(x) - x + 2;
    }

    // Метод секущих для поиска корня
    public static double secantMethod(double x0, double x1, double epsilon, int N) {
        int i = 0;

        while (i < N) {
            double f0 = function(x0);
            double f1 = function(x1);

            // Вычисляем новое приближение с помощью метода секущих
            double newX = x1 - (f1 * (x1 - x0)) / (f1 - f0);

            if (Math.abs(newX - x1) < epsilon) {
                return newX;
            }

            x0 = x1;
            x1 = newX;

            i++;
        }
        return Double.NaN; // Возвращаем NaN, если не удалось достичь точности за N итераций
    }

    public static void main(String[] args) {
        double x0 = 2.55;
        double x1 = x0 + 0.1; // Второе начальное приближение
        double epsilon = 1e-6;
        int N = 1000;

        double root = secantMethod(x0, x1, epsilon, N);

        if (!Double.isNaN(root)) {
            System.out.println("Найденное значение корня: " + root);
            System.out.println("Значение функции в корне: " + function(root));
        } else {
            System.out.println("Точность не достигнута за " + N + " шагов.");
        }
    }
}
