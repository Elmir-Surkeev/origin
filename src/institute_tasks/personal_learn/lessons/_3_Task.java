package institute_tasks.personal_learn.lessons;

//программа вычиляющая факториал числа
public class _3_Task {
    public static void main(String[] args) {
        int value = 5;
        int result = factoriall(value);
        System.out.println(result);
    }

    public static int factorial (int value) {
        int result = 1;
        for (int i = 1;  i <= value; i++)
            result*=value;
        return result;
    }

    public static int factoriall (int value){
        int result = 1;
        int i = 1;
        while (i <= value){
            result*=i;
            i++;
        }

        return result;
    }
}
