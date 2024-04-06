package personal_learn.lessons;


import java.util.Scanner;

public class _4_Task_array {
    public static void main(String[] args) {

        //Способ получение чисел из консоли
        Scanner sc  = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        if (a<c && a<b){
            System.out.println("is "+ a +" smaller then");
        }else if(b<a && b<c){
            System.out.println("is "+ b +" smaller then");
        }else if(c<b && c<a){
            System.out.println("is "+ c +" smaller then");
        }else{
            System.out.println("error");
        }
    }
}
