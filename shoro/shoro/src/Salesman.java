import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Salesman extends JFrame implements ActionListener {
    private JLabel text1;
    private JLabel text2;
    private JButton[] buttons;
    private static final String[] BUTTON_LABELS = {"Показать весь список товаров для продажи", "Сделать заказ товара",
            "Показать отчет по продаже", "Списать товар", "Сделать заказ отсутствующего товара",
            "Удалить заказ", "Выход"};

    public Salesman() {
        setTitle("Продавец");
        setSize(500, 500);
        setLocation(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        text1 = new JLabel("Приветствую, Продавец!");
        text1.setFont(new Font("Arial", Font.PLAIN, 16));
        text1.setBounds(100, 30, 400, 30);
        text1.setForeground(Color.white);

        text2 = new JLabel("Пожалуйста, выберите действие:");
        text2.setBounds(95, 65, 400, 30);
        text2.setFont(new Font("Arial", Font.PLAIN, 16));
        text2.setForeground(Color.white);

        buttons = new JButton[BUTTON_LABELS.length];
        for (int i = 0; i < BUTTON_LABELS.length; i++) {
            buttons[i] = new JButton(BUTTON_LABELS[i]);
            buttons[i].setBounds(100, 100 + i * 35, 300, 30);
            buttons[i].setFocusable(false);
            buttons[i].setActionCommand(BUTTON_LABELS[i].replaceAll("\\s+", ""));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setLayout(null);
        getContentPane().setBackground(Color.darkGray);
        add(text1);
        add(text2);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Показатьвесьсписоктоваровдляпродажи":
                showAvailableGoods();
                break;
            case "Сделатьзаказтовара":
                makeOrder();
                break;
            case "Показатьотчетпропродаже":
                showSalesReportByDate();
                break;
            case "Списатьтовар":
                removeGoods();
                break;
            case "Сделатьзаказотсутствующеготовара":
                makeBackorder();
                break;
            case "Удалитьзаказ":
                removeUnacceptedOrder();
                break;
            case "Выход":
                dispose();
                System.out.println("Программа завершена. До свидания!");
                break;
            default:
                System.out.println("Unknown command");
        }
    }

    private void showAvailableGoods() {
        try {
            File file = new File("goods.txt");
            Scanner scanner = new Scanner(file);
    
            if (!file.exists() || file.length() == 0) {
                System.out.println("Список товаров пуст.");
                return;
            }
    
            System.out.println("Список доступных товаров для продажи:");
    
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String itemName = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                System.out.println(itemName + ": " + quantity);
            }
    
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл товаров не найден.");
        }
    }
    
    private void makeOrder() {
        try {
            File file = new File("orders.txt");
            Scanner scanner = new Scanner(file);
    
            if (!file.exists() || file.length() == 0) {
                System.out.println("Список заказов пуст. Создайте новый заказ.");
            } else {
                System.out.println("Текущие заказы:");
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            }
            scanner.close();
    
            Scanner inputScanner = new Scanner(System.in);
            System.out.println("Введите название товара, который хотите заказать:");
            String itemName = inputScanner.nextLine();
    
            System.out.println("Введите количество товара:");
            int orderQuantity = inputScanner.nextInt();
    
            boolean itemExists = false;
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (parts[0].equalsIgnoreCase(itemName)) {
                    itemExists = true;
                    orderQuantity += Integer.parseInt(parts[1]);
                    break;
                }
            }
            scanner.close();
    
            if (itemExists) {
                updateOrder(file, itemName, orderQuantity);
            } else {
                FileWriter writer = new FileWriter(file, true);
                writer.write(itemName + " " + orderQuantity + "\n");
                writer.close();
            }
    
            System.out.println("Заказ успешно создан.");
        } catch (IOException e) {
            System.out.println("Ошибка при создании заказа: " + e.getMessage());
        }
    }
    
    private void showSalesReportByDate() {
        try {
            Scanner scanner = new Scanner(new File("sales_report.txt"));
    
            if (!scanner.hasNextLine()) {
                System.out.println("Отчет о продажах пуст.");
                return;
            }
    
            Scanner inputScanner = new Scanner(System.in);
            System.out.println("Введите дату в формате dd.MM.yyyy:");
            String dateString = inputScanner.nextLine();
    
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date requestedDate = dateFormat.parse(dateString);
    
            System.out.println("Отчет о продажах за " + dateString + ":");
    
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String saleDateStr = parts[0];
                Date saleDate = dateFormat.parse(saleDateStr);
    
                if (saleDate.equals(requestedDate)) {
                    System.out.println(line);
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println("Ошибка при чтении файла отчета о продажах: " + e.getMessage());
        }
    }
    

    
    private void removeGoods() {
        try {
            // Чтение файла с текущими запасами товаров
            File goodsFile = new File("goods.txt");
            Scanner scanner = new Scanner(goodsFile);
    
            if (!scanner.hasNextLine()) {
                System.out.println("Список товаров пуст.");
                return;
            }
    
            // Вывод списка товаров для выбора
            System.out.println("Список товаров:");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
    
            // Запросить у пользователя ввести название товара для списания
            Scanner inputScanner = new Scanner(System.in);
            System.out.println("Введите название товара для списания:");
            String selectedGood = inputScanner.nextLine();
    
            // Запросить у пользователя ввести количество для списания
            System.out.println("Введите количество для списания:");
            int quantityToRemove = inputScanner.nextInt();
    
            // Чтение текущего файла и запись нового файла с обновленными данными о запасах товаров
            FileReader fileReader = new FileReader(goodsFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
    
            StringBuilder newFileContent = new StringBuilder();
            String line;
            boolean goodFound = false;
    
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(" ");
                String goodName = parts[0];
                int quantity = Integer.parseInt(parts[1]);
    
                if (goodName.equals(selectedGood)) {
                    goodFound = true;
                    if (quantity >= quantityToRemove) {
                        quantity -= quantityToRemove;
                        newFileContent.append(goodName).append(" ").append(quantity).append("\n");
                        System.out.println("Товар успешно списан.");
    
                        // Если количество товара стало 0, добавляем его в файл out_of_stock.txt
                        if (quantity == 0) {
                            FileWriter outOfStockWriter = new FileWriter("out_of_stock.txt", true);
                            outOfStockWriter.write(goodName + " 0" + "\n");
                            outOfStockWriter.close();
                            System.out.println("Товар " + goodName + " добавлен в список товаров, отсутствующих в наличии.");
                        }
                    } else {
                        System.out.println("Ошибка: запрашиваемое количество больше имеющегося.");
                    }
                } else {
                    newFileContent.append(line).append("\n");
                }
            }
    
            if (!goodFound) {
                System.out.println("Ошибка: товар не найден.");
            }
    
            // Запись обновленных данных обратно в файл
            FileWriter fileWriter = new FileWriter(goodsFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newFileContent.toString());
    
            // Закрытие ресурсов
            bufferedReader.close();
            bufferedWriter.close();
            fileReader.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка при списании товара: " + e.getMessage());
        }
    }
    


    private void makeBackorder() {
        try {
            File file = new File("orders.txt");
            Scanner scanner = new Scanner(file);
    
            File outOfStockFile = new File("out_of_stock.txt");
            Scanner outOfStockScanner = new Scanner(outOfStockFile);
    
            if (!outOfStockScanner.hasNextLine()) {
                System.out.println("Нет отсутствующих товаров для заказа.");
                outOfStockScanner.close();
                return;
            }
    
            // Вывод списка отсутствующих товаров
            System.out.println("Отсутствующие товары:");
            while (outOfStockScanner.hasNextLine()) {
                System.out.println(outOfStockScanner.nextLine());
            }
            outOfStockScanner.close();
    
            // Запросить у пользователя ввести название отсутствующего товара для заказа
            Scanner inputScanner = new Scanner(System.in);
            System.out.println("Введите название отсутствующего товара для заказа:");
            String selectedGood = inputScanner.nextLine();
    
            // Запросить у пользователя ввести количество для заказа
            System.out.println("Введите количество для заказа:");
            int quantityToOrder = inputScanner.nextInt();
    
            // Проверяем, есть ли уже такой товар в списке заказов
            boolean itemExists = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (parts[0].equalsIgnoreCase(selectedGood)) {
                    itemExists = true;
                    // Обновляем количество товара
                    quantityToOrder += Integer.parseInt(parts[1]);
                    break;
                }
            }
            scanner.close();
    
            // Если товар уже существует, обновляем его количество
            if (itemExists) {
                updateOrder(file, selectedGood, quantityToOrder);
            } else {
                // Если товара нет в списке заказов, добавляем новый заказ
                FileWriter writer = new FileWriter(file, true);
                writer.write(selectedGood + " " + quantityToOrder + "\n");
                writer.close();
            }
    
            System.out.println("Заказ успешно создан.");
        } catch (IOException e) {
            System.out.println("Ошибка при создании заказа: " + e.getMessage());
        }
    }
    
    
    

    private void removeUnacceptedOrder() {
        try {
            // Чтение списка заказов из файла
            ArrayList<String> orders = new ArrayList<>();
            File file = new File("orders.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                orders.add(line);
            }
            scanner.close();
    
            // Предоставление пользователю списка заказов для выбора
            if (orders.isEmpty()) {
                System.out.println("Список текущих заказов пуст.");
                return;
            }
            
            System.out.println("Список текущих заказов:");
            for (int i = 0; i < orders.size(); i++) {
                System.out.println((i + 1) + ". " + orders.get(i));
            }
    
            // Предложить пользователю выбрать номер заказа для удаления
            System.out.print("Введите номер заказа для удаления: ");
            Scanner inputScanner = new Scanner(System.in);
            int orderNumber = inputScanner.nextInt();
    
            // Проверить, чтобы номер заказа был в пределах допустимого диапазона
            if (orderNumber >= 1 && orderNumber <= orders.size()) {
                // Удалить выбранный заказ из списка
                orders.remove(orderNumber - 1);
                System.out.println("Заказ успешно удален!");
    
                // Обновить файл с заказами
                FileWriter writer = new FileWriter(file);
                for (String order : orders) {
                    writer.write(order + "\n");
                }
                writer.close();
            } else {
                System.out.println("Неверный номер заказа.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при удалении заказа: " + e.getMessage());
        }
    }
        private void updateOrder(File file, String itemName, int newQuantity) throws IOException {
        // Читаем файл и создаем новый список заказов с обновленным количеством товара
        Scanner scanner = new Scanner(file);
        StringBuilder newData = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if (parts[0].equalsIgnoreCase(itemName)) {
                newData.append(itemName).append(" ").append(newQuantity).append("\n");
            } else {
                newData.append(line).append("\n");
            }
        }
        scanner.close();
    
        // Перезаписываем файл с обновленными данными о заказах
        FileWriter writer = new FileWriter(file);
        writer.write(newData.toString());
        writer.close();
    }
}