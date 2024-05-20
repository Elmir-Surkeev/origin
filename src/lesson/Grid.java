package lesson;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Grid {
    private int n;
    private int[][] table;
    private int[][] table2;
    private int lives;
    private int remainingCells;

    public Grid() {
        this(3);
    }

    public Grid(int n) {
        this.n = n;
        this.table = new int[n * n][n * n];
        generateTable();
        System.out.println("The base table is ready!");
    }

    private void generateTable() {
        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n * n; j++) {
                table[i][j] = ((i * n + i / n + j) % (n * n) + 1);
            }
        }
    }

    public void transpose() {
        int[][] transposedTable = new int[n * n][n * n];
        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n * n; j++) {
                transposedTable[j][i] = table[i][j];
            }
        }
        table = transposedTable;
    }

    public void show1() {
        for (int i = 0; i < n * n; i++) {
            System.out.println(Arrays.toString(table[i]));
        }
    }

    public void show2() {
        for (int i = 0; i < n * n; i++) {
            System.out.println(Arrays.toString(table2[i]));
        }
    }

    public void swapRows(int row1, int row2) {
        int[] temp = table[row1];
        table[row1] = table[row2];
        table[row2] = temp;
    }

    public void swapColumns(int col1, int col2) {
        for (int i = 0; i < n * n; i++) {
            int temp = table[i][col1];
            table[i][col1] = table[i][col2];
            table[i][col2] = temp;
        }
    }

    public void randomSwapColumns(int numSwaps) {
        Random random = new Random();
        for (int i = 0; i < numSwaps; i++) {
            int col1 = random.nextInt(n * n);
            int col2 = random.nextInt(n * n);
            swapColumns(col1, col2);
        }
    }

    public void randomSwapRows(int numSwaps) {
        Random random = new Random();
        for (int i = 0; i < numSwaps; i++) {
            int col1 = random.nextInt(n * n);
            int col2 = random.nextInt(n * n);
            swapRows(col1, col2);
        }
    }

    public void createRandomMatrix(Grid grid) {
        Random random = new Random();
        grid.randomSwapRows(random.nextInt(9));
        grid.randomSwapColumns(random.nextInt(9));
        grid.transpose();
        grid.show1();
    }

    public void removeRandomNumbersFromRows() {
        Random random = new Random();
        for (int i = 0; i < n * n; i++) {
            int numToRemove = random.nextInt(n * n);
            for (int j = 0; j < numToRemove; j++) {
                int indexToRemove = random.nextInt(n * n);
                table2[i][indexToRemove] = 0;
                lives++;
                remainingCells++;
            }
        }
        lives = lives - lives / 3;
    }

    public void copyMatrix() {
        table2 = new int[n * n][n * n];
        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n * n; j++) {
                table2[i][j] = table[i][j];
            }
        }
    }

    private void makeMove(int row, int col, int num) {
        if (table[row][col] == num) {
            table2[row][col] = num;
            remainingCells--;
        } else {
            System.out.println("минус жизнь");
            lives--;
        }
    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.createRandomMatrix(grid);
        grid.copyMatrix();
        grid.removeRandomNumbersFromRows();
        System.out.println();
        grid.show2();
        Scanner scanner = new Scanner(System.in);

        while (grid.getLives() > 0) {
            System.out.println("кол-во жизней: " + grid.getLives());
            System.out.println("чтобы выйти вводите 0 0 0 ");
            System.out.println("вводите строку столбец и значение(через пробел, пример 2 4 5)");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            int value = scanner.nextInt();
            if (row == -1 && col == -1 && value == 0)
                break;
            grid.makeMove(row, col, value);
            if (grid.getRemainingCells() == 0) {
                grid.show2();
                System.out.println("вы выиграли!!!");
                break;
            } else if (grid.getLives() == 0) {
                System.out.println("вы проиграли количество жизней равно 0 !!!");
                break;
            }
            grid.show2();
        }

        scanner.close();

    }

    public int getLives() {
        return lives;
    }

    public int getRemainingCells() {
        return remainingCells;
    }
}
