package personal_learn;

public class ForExample {
    public static void main(String[] args) {
        for (int value = 1; value < 83; value++){
            if (value == 5){
                continue;
                //break;
            }
            System.out.println(value);
        }
        for (int value = 10; value > 0; value -- ){
            System.out.println("hey");
        }
    }
}
