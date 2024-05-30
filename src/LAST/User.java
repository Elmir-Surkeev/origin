package LAST;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String password;

    // Хранение пользователей в хэш-карте
    private static Map<String, String> users = new HashMap<>();

    // Предустановленные пользователи
    static {
        users.put("user1", "password1");
        users.put("user2", "password2");
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Метод для регистрации нового пользователя
    public static boolean register(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, password);
            return true;
        }
        return false;
    }

    // Метод для проверки логина и пароля
    public static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
