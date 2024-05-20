package institute_tasks.algoritmes.third_colloquium.SRS;

import java.util.Scanner;

public class MessageStore {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("текст из файла message.txt '  мина    находится    на    6 уровне ' ");
        String message = scanner.nextLine();

        System.out.print("Введите ключ шифрования (количество столбцов): ");
        int key = scanner.nextInt();

        String encryptedMessage = encrypt(message, key);
        System.out.println("Зашифрованное сообщение: " + encryptedMessage);

        scanner.close();
    }

    // Метод для шифрования сообщения шифром Скитала
    public static String encrypt(String message, int key) {
        StringBuilder encryptedMessage = new StringBuilder();

        // Создаем массив символов для исходного сообщения
        char[] messageChars = message.toCharArray();

        // Создаем массив символов для зашифрованного сообщения
        char[][] grid = new char[key][(int) Math.ceil((double) messageChars.length / key)];

        // Заполняем сетку символами исходного сообщения
        int row = 0, col = 0;
        for (char ch : messageChars) {
            grid[row][col] = ch;
            row++;
            if (row == key) {
                row = 0;
                col++;
            }
        }

        // Считываем зашифрованное сообщение из сетки
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    encryptedMessage.append(grid[i][j]);
                }
            }
        }

        return encryptedMessage.toString();
    }
}
