package com.example.Web.App;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<String> users = new ArrayList<>();

    public UserService() {
        users.add("Alice");
        users.add("Bob");
        users.add("Charlie");
    }

    public List<String> getAllUsers() {
        return new ArrayList<>(users); // возвращаем копию, чтобы избежать мутаций извне
    }

    public void addUser(String username) {
        if (username != null && !username.trim().isEmpty()) {
            users.add(username.trim());
        }
    }

}
