package shoro.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class DeliveryMan extends JFrame implements ActionListener {

    JLabel text1;
    JLabel text2;
    JLabel backGround;
    JRadioButton option1;
    JRadioButton option2;
    JRadioButton option3;
    JRadioButton option4;
    JRadioButton option5;
    JButton collectEarningsButton;
    JButton calculateEarningsButton;
    ButtonGroup buttonGroup;

    public DeliveryMan() {
        this.setTitle("Доставщик");
        this.setIconImage(frame.shoroIcon.getImage());
        this.setSize(500, 500);
        this.setLocation(600, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        text1 = new JLabel("Приветствую дорогой, Доставщик!");
        text1.setFont(frame.font);
        text1.setBounds(100, 30, 400, 30);
        text1.setForeground(Color.white);

        text2 = new JLabel("Пожалуйста выберите номер меню!");
        text2.setFont(frame.font);
        text2.setBounds(95, 65, 350, 30);
        text2.setForeground(Color.white);

        backGround = new JLabel();
        backGround.setIcon(frame.shoroImage);

        option1 = new JRadioButton("Показать список товаров для доставки ");
        option2 = new JRadioButton("Показать доставленные заказы");
        option3 = new JRadioButton("Доставить заказ");
        option4 = new JRadioButton("Показать количество доставленных товаров");
        option5 = new JRadioButton("Показать количество заказанных товаров ");

        calculateEarningsButton = new JButton("Подсчитать заработок");
        calculateEarningsButton.setBounds(100, 320, 300, 30);
        calculateEarningsButton.setFocusable(false);
        calculateEarningsButton.addActionListener(this);
        calculateEarningsButton.addActionListener(this);
        
        collectEarningsButton = new JButton("Забрать заработанные деньги");
        collectEarningsButton.setBounds(100, 360, 300, 30);
        collectEarningsButton.setFocusable(false);
        collectEarningsButton.addActionListener(this);

        this.add(collectEarningsButton);
        this.add(calculateEarningsButton);
      
        buttonGroup = new ButtonGroup();
        buttonGroup.add(option1);
        buttonGroup.add(option2);
        buttonGroup.add(option3);
        buttonGroup.add(option4);
        buttonGroup.add(option5);

        option1.setBounds(100, 100, 300, 30);
        option1.setFocusable(false);
        option1.addActionListener(this);

        option2.setBounds(100, 135, 300, 30);
        option2.setFocusable(false);
        option2.addActionListener(this);

        option3.setBounds(100, 170, 300, 30);
        option3.setFocusable(false);
        option3.addActionListener(this);

        option4.setBounds(100, 205, 300, 30);
        option4.setFocusable(false);
        option4.addActionListener(this);

        option5.setBounds(100, 240, 300, 30);
        option5.setFocusable(false);
        option5.addActionListener(this);

        this.add(text1);
        this.add(text2);
        this.add(option1);
        this.add(option2);
        this.add(option3);
        this.add(option4);
        this.add(option5);
        this.add(backGround);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == option1) {
            showDeliveryList();
        }
        if (e.getSource() == option2) {
            showDeliveredOrdersReport();
        }
        if (e.getSource() == option3) {
            deliverOrder();
        }
        if (e.getSource() == option4) {
            showDeliveredItemsCount();
        }
        if (e.getSource() == option5) {
            showOrderedItemsCount();
        }
        if (e.getSource() == collectEarningsButton) {
            collectEarnings();
        } if (e.getSource() == calculateEarningsButton) {
            calculateEarnings();
        }
    }





    private void showDeliveryList() {
        try {
            File file = new File("confirm_order.txt");
            Scanner scanner = new Scanner(file);
            
            if (!scanner.hasNextLine()) {
                System.out.println("Нет заказов для доставки.");
            } else {
                System.out.println("Список товаров для доставки:");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                }
            }
            
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: Файл заказов не найден.");
        }
    }
    



    private void showDeliveredOrdersReport() {
        try {
            File file = new File("delivered_orders.txt");
            Scanner scanner = new Scanner(file);
            
            if (!scanner.hasNextLine()) {
                System.out.println("Нет доставленных заказов.");
            } else {
                System.out.println("Отчет о доставленных заказах:");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                }
            }
            
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: Файл с информацией о доставленных заказах не найден.");
        }
    }
    



    private void deliverOrder() {
        try {
            File confirmOrderFile = new File("confirm_order.txt");
            Scanner scanner = new Scanner(confirmOrderFile);
    
            if (!scanner.hasNextLine()) {
                System.out.println("Нет подтвержденных заказов для доставки.");
                scanner.close();
                return;
            }
    
            System.out.println("Список товаров для доставки:");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
    
            Scanner inputScanner = new Scanner(System.in);
            System.out.print("Введите название товара для доставки: ");
            String item = inputScanner.nextLine().trim();
            System.out.print("Введите количество товара для доставки: ");
            int quantity = inputScanner.nextInt();
    
            scanner = new Scanner(confirmOrderFile);
            boolean found = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (parts.length >= 2 && parts[0].equals(item)) {
                    int availableQuantity = Integer.parseInt(parts[1]);
                    if (quantity <= availableQuantity) {
                        found = true;
                        updateConfirmOrderFile(confirmOrderFile, item, availableQuantity - quantity);
                        recordDeliveredOrder(item, quantity);
                        System.out.println("Заказ доставлен успешно.");
                        removeItemFromOutOfStock(item);
                    } else {
                        System.out.println("Недостаточное количество товара для доставки.");
                    }
                    break;
                }
            }
            scanner.close();
    
            if (!found) {
                System.out.println("Товар для доставки не найден в подтвержденных заказах.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при обработке заказа: " + e.getMessage());
        }
    }
    
    


    private void removeItemFromOutOfStock(String item) throws IOException {
        File outOfStockFile = new File("out_of_stock.txt");
        Scanner scanner = new Scanner(outOfStockFile);
        StringBuilder newContent = new StringBuilder();
        boolean removed = false;
    
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if (parts.length >= 2 && parts[0].equals(item)) {
                removed = true;
                continue; // Пропускаем запись товара, который нужно удалить
            }
            newContent.append(line).append("\n");
        }
        scanner.close();
    
        if (removed) {
            System.out.println("Удаление товара \"" + item + "\" из списка out_of_stock.txt...");
            System.out.println("Новое содержимое списка:");
            System.out.println(newContent.toString());
    
            FileWriter writer = new FileWriter(outOfStockFile);
            writer.write(newContent.toString());
            writer.close();
            System.out.println("Товар \"" + item + "\" удален из списка out_of_stock.txt.");
        } else {
            System.out.println("Товар \"" + item + "\" не найден в списке out_of_stock.txt.");
        }
    }
    
    
    
    
    // Метод для обновления файла с подтвержденными заказами
    private void updateConfirmOrderFile(File file, String item, int quantity) throws IOException {
        Scanner scanner = new Scanner(file);
        StringBuilder newData = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith(item)) {
                newData.append(item).append(" ").append(quantity).append("\n");
            } else {
                newData.append(line).append("\n");
            }
        }
        scanner.close();
    
        FileWriter writer = new FileWriter(file);
        writer.write(newData.toString());
        writer.close();
    }
    



    // Метод для записи информации о доставленном заказе в файл goods.txt
private void recordDeliveredOrder(String item, int quantity) {
    try {
        File file = new File("goods.txt");
        Scanner scanner = new Scanner(file);
        StringBuilder newData = new StringBuilder();
        boolean found = false;

        // Проверяем, есть ли уже запись для этого товара, чтобы увеличить количество
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if (parts[0].equals(item)) {
                int currentQuantity = Integer.parseInt(parts[1]);
                newData.append(item).append(" ").append(currentQuantity + quantity).append("\n");
                found = true;
            } else {
                newData.append(line).append("\n");
            }
        }
        scanner.close();

        // Если записи для этого товара не было найдено, добавляем новую
        if (!found) {
            newData.append(item).append(" ").append(quantity).append("\n");
        }

        // Записываем обновленные данные обратно в файл
        FileWriter writer = new FileWriter(file);
        writer.write(newData.toString());
        writer.close();
    } catch (IOException e) {
        System.out.println("Ошибка при записи доставленного заказа в файл goods.txt: " + e.getMessage());
    }

    // Теперь добавим запись в файл delivered_orders.txt
    try {
        // Создаем или загружаем файл delivered_orders.txt
        File deliveredOrdersFile = new File("delivered_orders.txt");
        if (!deliveredOrdersFile.exists()) {
            deliveredOrdersFile.createNewFile();
        }

        // Создаем карту для хранения суммарного количества доставленного товара
        Map<String, Integer> deliveredItems = new HashMap<>();

        // Читаем данные из файла delivered_orders.txt и сохраняем их в карту
        Scanner scanner = new Scanner(deliveredOrdersFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String itemName = parts[0];
            int itemQuantity = Integer.parseInt(parts[1]);
            deliveredItems.put(itemName, itemQuantity);
        }
        scanner.close();

        // Обновляем суммарное количество доставленного товара для данного товара
        if (deliveredItems.containsKey(item)) {
            deliveredItems.put(item, deliveredItems.get(item) + quantity);
        } else {
            deliveredItems.put(item, quantity);
        }

        // Записываем обновленные данные в файл delivered_orders.txt
        FileWriter writer = new FileWriter(deliveredOrdersFile);
        for (Map.Entry<String, Integer> entry : deliveredItems.entrySet()) {
            writer.write(entry.getKey() + " " + entry.getValue() + "\n");
        }
        writer.close();

    } catch (IOException e) {
        System.out.println("Ошибка при записи доставленного заказа в файл delivered_orders.txt: " + e.getMessage());
    }
}
    
    
    

private void showDeliveredItemsCount() {
    try {
        File deliveredOrdersFile = new File("delivered_orders.txt");
        if (!deliveredOrdersFile.exists() || deliveredOrdersFile.length() == 0) {
            System.out.println("Нет информации о доставленных товарах.");
            return;
        }

        Scanner scanner = new Scanner(deliveredOrdersFile);
        Map<String, Integer> deliveredItems = new HashMap<>();
        int totalDeliveredItems = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if (parts.length >= 2) {
                String itemName = parts[0];
                int itemCount = Integer.parseInt(parts[1]);
                deliveredItems.put(itemName, deliveredItems.getOrDefault(itemName, 0) + itemCount);
                totalDeliveredItems += itemCount;
            }
        }
        scanner.close();

        System.out.println("Количество доставленных товаров:");
        for (Map.Entry<String, Integer> entry : deliveredItems.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Общее количество доставленных товаров: " + totalDeliveredItems);
    } catch (IOException e) {
        System.out.println("Ошибка при чтении файла с информацией о доставленных товарах: " + e.getMessage());
    }
}




// Метод для отображения количества заказанных товаров и их общей суммы
private void showOrderedItemsCount() {
    try {
        File ordersFile = new File("confirm_order.txt");
        if (!ordersFile.exists() || ordersFile.length() == 0) {
            System.out.println("Нет информации о заказанных товарах.");
            return;
        }

        Scanner scanner = new Scanner(ordersFile);
        Map<String, Integer> orderedItems = new HashMap<>();
        int totalOrderedItems = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if (parts.length >= 2) {
                String itemName = parts[0];
                int itemCount = Integer.parseInt(parts[1]);
                orderedItems.put(itemName, orderedItems.getOrDefault(itemName, 0) + itemCount);
                totalOrderedItems += itemCount;
            }
        }
        scanner.close();

        System.out.println("Количество заказанных товаров:");
        for (Map.Entry<String, Integer> entry : orderedItems.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Общее количество заказанных товаров: " + totalOrderedItems);
    } catch (IOException e) {
        System.out.println("Ошибка при чтении файла с информацией о заказанных товарах: " + e.getMessage());
    }
}
// Метод для подсчета заработка курьера
private double calculateEarnings() {
    try {
        File deliveredOrdersFile = new File("delivered_orders.txt");
        if (!deliveredOrdersFile.exists() || deliveredOrdersFile.length() == 0) {
            System.out.println("Нет информации о доставленных заказах.");
            return 0;
        }

        Scanner scanner = new Scanner(deliveredOrdersFile);
        double earnings = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine(); // Пропускаем строку, так как нам не важно название товара, только количество
            earnings += 3; // За каждый товар платим 2 сома
        }
        System.out.println("Ваш заработок: " + earnings + " сом");
        scanner.close();

        return earnings;
    } catch (IOException e) {
        System.out.println("Ошибка при чтении файла с доставленными заказами: " + e.getMessage());
        return 0;
    }
}

// Метод для сбора заработка и очистки файла с доставленными заказами
private void collectEarnings() {
    double earnings = calculateEarnings();
    System.out.println("Ваш заработок: " + earnings + " сом");
    
    // Очистка файла с доставленными заказами
    try {
        File deliveredOrdersFile = new File("delivered_orders.txt");
        if (deliveredOrdersFile.exists()) {
            deliveredOrdersFile.delete();
            System.out.println("Файл с доставленными заказами очищен.");
        }
    } catch (Exception e) {
        System.out.println("Ошибка при очистке файла с доставленными заказами: " + e.getMessage());
    }
}
}
