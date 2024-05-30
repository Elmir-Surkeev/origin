import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AdminLogin extends JFrame implements ActionListener {
    JLabel loginLabel, passwordLabel, message;
    JTextField loginField;
    JPasswordField passwordField;
    JButton loginButton;

    AdminLogin() {
        this.setTitle("Вход для администратора");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        loginLabel = new JLabel("Логин:");
        loginLabel.setBounds(50, 20, 100, 30);
        loginField = new JTextField();
        loginField.setBounds(150, 20, 200, 30);

        passwordLabel = new JLabel("Пароль:");
        passwordLabel.setBounds(50, 60, 100, 30);
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 60, 200, 30);

        loginButton = new JButton("Войти");
        loginButton.setBounds(150, 100, 100, 30);
        loginButton.addActionListener(this);

        message = new JLabel("");
        message.setBounds(50, 140, 300, 30);

        this.add(loginLabel);
        this.add(loginField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(loginButton);
        this.add(message);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());

            // Проверяем логин и пароль
            if (login.equals("admin") && password.equals("admin")) {
                // Открываем окно регистрации новых пользователей
                new AdminRegistration();
                // Закрываем окно входа
                this.dispose();
            } else {
                message.setText("Неправильный логин или пароль!");
            }
        }
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}
