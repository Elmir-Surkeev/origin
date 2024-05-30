
package shoro.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class AdminRegistration extends JFrame implements ActionListener {
    JLabel loginLabel, passwordLabel, message;
    JTextField loginField;
    JPasswordField passwordField;
    JButton registerButton;

    AdminRegistration() {
        this.setTitle("Регистрация нового пользователя");
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

        registerButton = new JButton("Зарегистрировать");
        registerButton.setBounds(150, 100, 150, 30);
        registerButton.addActionListener(this);

        message = new JLabel("");
        message.setBounds(50, 140, 300, 30);

        this.add(loginLabel);
        this.add(loginField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(registerButton);
        this.add(message);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());

            // Проверяем, что поля логина и пароля не пустые
            if (!login.isEmpty() && !password.isEmpty()) {
                // Регистрируем нового пользователя, записывая логин и пароль в файлы
                try {
                    FileWriter loginWriter = new FileWriter("logins.txt", true);
                    loginWriter.write("\n" + login);
                    loginWriter.close();

                    FileWriter passwordWriter = new FileWriter("passwords.txt", true);
                    passwordWriter.write("\n" + password);
                    passwordWriter.close();

                    message.setText("Пользователь успешно зарегистрирован!");
                } catch (IOException ex) {
                    message.setText("Ошибка при регистрации пользователя!");
                }
            } else {
                message.setText("Логин и пароль не должны быть пустыми!");
            }
        }
    }

    public static void main(String[] args) {
        new AdminRegistration();
    }
}
