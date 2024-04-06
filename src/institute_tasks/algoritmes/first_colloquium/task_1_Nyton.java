package institute_tasks.algoritmes.first_colloquium;

public class task_1_Nyton {

    // Функция sin(x) - x + 2
    public static double function(double x) {
        return Math.sin(x) - x + 2;
    }
    // Производная функции sin(x) - x + 2
    public static double derivative(double x) {
        return Math.cos(x) - 1;
    }

    // Метод Ньютона для поиска корня
    public static double newtonMethod(double x0, double epsilon, int N) {
        int i = 0;
        double x1 = x0;

        while (i < N) {
            double f = function(x1);
            double df = derivative(x1);

            if (Math.abs(f) < epsilon) {
                return x1;
            }

            x1 = x1 - f / df;

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

        double root = newtonMethod(x0, epsilon, N);

        if (!Double.isNaN(root)) {
            System.out.println("Найденное значение корня: " + root);
            System.out.println("Значение функции в корне: " + function(root));
        } else {
            System.out.println("Точность не достигнута за " + N + " шагов.");
        }
    }
}

