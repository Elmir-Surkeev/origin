import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public abstract class Methods extends JFrame {
    public static void showFile(String path) {
        try {
            FileReader reader = new FileReader(path);
            int data = reader.read();
            while (data != -1) {
                System.out.print((char) data);
                data = reader.read();
            }
            reader.close();

        } catch (Exception ex) {
            System.out.println("Неверный путь до файла :(");
        }
    }
    public static int getQuantityOfGoods(String path) {
        int lines = 0;
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                lines++;
                scanner.nextLine();
            }
            scanner.close();
        } catch (Exception ex) {
            System.out.println("Неверный путь до файла :(");
        }
        return (lines);
    }

}
