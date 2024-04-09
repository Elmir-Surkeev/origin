package institute_tasks.proggraming_language.second_colloquium.course_task;

public class Course_2_task {
    //cos(x^5) + x^4 - 345.3 * x - 23 = 0
  //  на отрезке [0; 10] с точностью по x не хуже, чем 0.001. Известно, что на этом промежутке корень единственный.
//    Используйте для этого метод деления отрезка пополам (и рекурсию).
        // Функция, которую будем анализировать
        public static double function(double x) {
            return Math.cos(Math.pow(x, 5)) + Math.pow(x, 4) - 345.3 * x - 23;
        }
        // Метод деления отрезка пополам для нахождения корня уравнения
        public static double bisectionMethod(double a, double b, double epsilon) {
            double c = (a + b) / 2;
            if (Math.abs(function(c)) < epsilon) {
                return c;
            } else if (function(c) * function(a) < 0) {
                return bisectionMethod(a, c, epsilon);
            } else {
                return bisectionMethod(c, b, epsilon);
            }
        }
        public static void main(String[] args) {
            double a = 0;
            double b = 10;
            double epsilon = 0.001;
            double root = bisectionMethod(a, b, epsilon);
            System.out.println("Root: " + root);
        }
}
