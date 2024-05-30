import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Provider extends JFrame implements ActionListener {
    JLabel text1;
    JLabel text2;
    JRadioButton option1;
    JRadioButton option2;
    JRadioButton option3;
    JRadioButton option4;
    JRadioButton option5;
    ButtonGroup buttonGroup;
    JLabel backGround;

    Provider() {
        this.setTitle("Поставщик");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setIconImage(frame.shoroIcon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(600,200);

        text1 = new JLabel("Приветствую дорогой, Поставщик!");
        text1.setFont(frame.font);
        text1.setBounds(100,30,400,30);
        text1.setForeground(Color.white);

        text2 = new JLabel("Пожалуйста выберите номер меню!");
        text2.setFont(frame.font);
        text2.setBounds(95,65,400,30);
        text2.setForeground(Color.white);

        backGround = new JLabel();
        backGround.setIcon(frame.shoroImage);

        option1 = new JRadioButton("Показать список товаров для поставки");
        option2 = new JRadioButton("Показать количество поставляемого товара");
        option3 = new JRadioButton("Показать товар с самым большим количеством заказов для доставки");
        option4 = new JRadioButton("Показать товар с самым меньшим количеством заказов для доставки");
        option5 = new JRadioButton("Выход");


        buttonGroup = new ButtonGroup();
        buttonGroup.add(option1);
        buttonGroup.add(option2);
        buttonGroup.add(option3);
        buttonGroup.add(option4);
        buttonGroup.add(option5);

        option1.setBounds(100,100,300,30);
        option1.setFocusable(false);
        option1.addActionListener(this);

        option2.setBounds(100,135,300,30);
        option2.setFocusable(false);
        option2.addActionListener(this);

        option3.setBounds(100,170,300,30);
        option3.setFocusable(false);
        option3.addActionListener(this);

        option4.setBounds(100,205,300,30);
        option4.setFocusable(false);
        option4.addActionListener(this);

        option5.setBounds(100,240,300,30);
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
            Methods.showFile("need_material.txt");
        }
        if (e.getSource() == option2) {
            System.out.println(Methods.getQuantityOfGoods("need_material.txt"));
        }
        if (e.getSource() == option3) {
            mostPopularGood("delivered.txt");
        }
        if (e.getSource() == option4) {
            LeastPopularGood("delivered.txt");
        }
        if (e.getSource() == option5) {
            System.out.println("Программа завершена, мы будем рады вашему возвращению!");
            this.dispose();
        }

    }
    private void mostPopularGood(String path){
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            HashMap<String, Integer> countMap = new HashMap<>();

            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                for (String word : words) {
                    if (countMap.containsKey(word)) {
                        countMap.put(word, countMap.get(word) + 1);
                    } else {
                        countMap.put(word, 1);
                    }
                }
            }

            HashMap.Entry<String, Integer> maxEntry = null;
            for (HashMap.Entry<String, Integer> entry : countMap.entrySet()) {
                if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                    maxEntry = entry;
                }
            }

            System.out.println("Товар с наибольшим количеством заказов: " + maxEntry.getKey());

            fileReader.close();

        }catch (Exception e){}
    }
    private void LeastPopularGood(String path){
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            HashMap<String, Integer> countMap = new HashMap<>();

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (countMap.containsKey(word)) {
                        countMap.put(word, countMap.get(word) + 1);
                    } else {
                        countMap.put(word, 1);
                    }
                }
            }

            Set<HashMap.Entry<String, Integer>> entries = countMap.entrySet();
            HashMap.Entry<String, Integer> minEntry = entries.stream().min(Map.Entry.comparingByValue()).get();

            System.out.println("Товар с наименьшим количеством заказов: " + minEntry.getKey());

            bufferedReader.close();
            fileReader.close();

        }catch (Exception e){}
    }

}
