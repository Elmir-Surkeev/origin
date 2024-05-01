package institute_tasks.proggraming_language.third_colloquium;

import java.util.Scanner;

public class task_3_sudocu {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;
    private static int[][] board = new int[SIZE][SIZE];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Заполнение игрового поля
        initializeBoard();

        // Вывод игрового поля
        printBoard();

        while (!isSudokuSolved()) {
            // Ввод координат клетки и значения
            System.out.print("Enter row (1-9): ");
            int row = scanner.nextInt() - 1;

            System.out.print("Enter column (1-9): ");
            int column = scanner.nextInt() - 1;

            System.out.print("Enter value (1-9): ");
            int value = scanner.nextInt();

            // Проверка, можно ли поставить значение в указанную клетку
            if (isValidMove(row, column, value)) {
                board[row][column] = value;
                printBoard();
            } else {
                System.out.println("Invalid move! Please try again.");
            }
        }

        System.out.println("Congratulations! Sudoku solved!");
    }

    // Инициализация начального состояния игрового поля (пустые клетки)
    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // Печать текущего состояния игрового поля
    private static void printBoard() {
        System.out.println("-------------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    System.out.print("  ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
                if ((j + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((i + 1) % 3 == 0) {
                System.out.println("-------------------");
            }
        }
    }

    // Проверка, можно ли поставить значение в указанную клетку
    private static boolean isValidMove(int row, int column, int value) {
        // Проверка строки и столбца
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == value || board[i][column] == value) {
                return false;
            }
        }
        // Проверка квадрата 3x3
        int startRow = row - row % 3;
        int startColumn = column - column % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startColumn; j < startColumn + 3; j++) {
                if (board[i][j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    // Проверка, решено ли судоку
    private static boolean isSudokuSolved() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}

