package service;

import java.util.List;
import model.User;

public interface UserService {
    void add(User user);

    List<User> listUsers();
}
