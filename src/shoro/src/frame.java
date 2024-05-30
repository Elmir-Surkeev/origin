package shoro.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class frame extends JFrame implements ActionListener {
    JComboBox<String> comboBox;
    DefaultListCellRenderer listCellRenderer = new DefaultListCellRenderer();
    public static ImageIcon shoroIcon = new ImageIcon("шоро.png");
    JLabel backGround = new JLabel();
    JLabel text1 = new JLabel();
    JLabel text2 = new JLabel();
    public static ImageIcon shoroImage = new ImageIcon("shoroImage1.png");
    public static Font font = new Font("Arial",Font.PLAIN,20);
    JTextField password = new JTextField();
    JTextField login = new JTextField();
    JButton submit = new JButton("Продолжить");
    JButton clearForLogin = new JButton("Очистить");
    JButton clearForPassword = new JButton("Очистить");
    JButton quit = new JButton("Выход");
    JLabel chooseTypeOfAccount = new JLabel();

    JButton adminLoginButton;

    public frame(){
        String[] persons= {"Продавец","Доставщик","Поставщик"};
        comboBox = new JComboBox<>(persons);
        comboBox.addActionListener(this);
        comboBox.setBounds(290,115,150,30);
        comboBox.setBackground(Color.red);
        comboBox.setForeground(Color.white);
        listCellRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        comboBox.setRenderer(listCellRenderer);





        backGround.setIcon(shoroImage);
        text1.setText("Добро пожаловать!");
        text2.setText("Пожалуйста авторизуйтесь!");
        text1.setBounds(175,30,200,25);
        text1.setForeground(Color.white);
        text1.setFont(font);
        text2.setBounds(135,55,300,25);
        text2.setFont(font);
        text2.setForeground(Color.white);




        chooseTypeOfAccount.setBounds(60,100,250,60);
        chooseTypeOfAccount.setText("Выберите тип аккаунта");
        chooseTypeOfAccount.setFont(new Font("Arial",Font.PLAIN,20));
        chooseTypeOfAccount.setForeground(Color.white);

        adminLoginButton = new JButton("Вход для администратора");
        adminLoginButton.setBounds(130, 400, 200, 30);
        adminLoginButton.addActionListener(this);

        this.add(adminLoginButton); // Добавляем кнопку на экран


        password.setFont(font);
        password.setHorizontalAlignment(SwingConstants.CENTER);
        password.setText("Введите пароль");
        password.setBounds(30,230,300,30);
        password.addActionListener(this);




        login.setFont(font);
        login.setHorizontalAlignment(SwingConstants.CENTER);
        login.setText("Введите логин");
        login.setBounds(30,195,300,30);




        submit.setBounds(130,265,200,30);
        submit.setFont(font);
        submit.addActionListener(this);



        quit.setFont(font);
        quit.setHorizontalAlignment(SwingConstants.CENTER);
        quit.setBounds(130,350,200,30);
        quit.addActionListener(this);



        clearForLogin.setBounds(335,195,120,30);
        clearForLogin.setFont(font);
        clearForLogin.addActionListener(this);

        clearForPassword.setBounds(335,230,120,30);
        clearForPassword.setFont(font);
        clearForPassword.addActionListener(this);

        this.setTitle("Cola");
        this.setResizable(false);
        this.setLocation(600,200);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFont(new Font("Arial", Font.PLAIN,15));
        this.setIconImage(shoroIcon.getImage());


        this.add(text2);
        this.add(text1);
        this.add(chooseTypeOfAccount);
        this.add(submit);
        this.add(clearForLogin);
        this.add(clearForPassword);
        this.add(password);
        this.add(login);
        this.add(quit);
        this.add(comboBox);
        this.add(backGround);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminLoginButton) {
            // Открываем окно для входа администратора при нажатии на кнопку "Вход для администратора"
            new AdminLogin();
        }
        if (e.getSource() == quit){
            System.out.println("Программа завершена, мы будем рады вашему возвращению!");
            this.dispose();
        }
        if (e.getSource() == clearForLogin){
            login.setText("");
        }
        if (e.getSource() == clearForPassword){
            password.setText("");
        }
        if (e.getSource() == submit){
            if (comboBox.getSelectedItem() == "Продавец"){
                try {
                    searchLogin(login.getText());
                    searchPassword(password.getText());
                    if(searchLogin(login.getText()) && searchPassword(password.getText())){
                        new Salesman();
                        this.dispose();
                    }
                    else{
                        System.out.println("Неверный логин и(или) пароль :(");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
            if (comboBox.getSelectedItem() == "Доставщик"){
                try {
                    searchLogin(login.getText());
                    searchPassword(password.getText());
                    if(searchLogin(login.getText()) && searchPassword(password.getText())){
                        new DeliveryMan();
                        this.dispose();
                    }
                    else{
                        System.out.println("Неверный логин и(или) пароль :(");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
                if (comboBox.getSelectedItem() == "Поставщик"){
                try {
                    searchLogin(login.getText());
                    searchPassword(password.getText());
                    if(searchLogin(login.getText()) && searchPassword(password.getText())){
                        new Provider();
                        this.dispose();
                    }
                    else{
                        System.out.println("Неверный логин и(или) пароль :(");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }
    private boolean searchLogin(String login) throws IOException {
        File file = new File("logins.txt");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            String data = scanner.nextLine();
            if (data.contains(login)){
                return true;
            }
        }
        return false;
    }
    private boolean searchPassword(String password) throws IOException {
        File file = new File("passwords.txt");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            String data = scanner.next();
            if (data.equals(password)){
                return true;
            }
        }
        return false;
    }

}
