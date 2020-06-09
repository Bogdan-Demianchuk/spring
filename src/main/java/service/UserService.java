package service;

import java.util.List;
import model.User;

public interface UserService {
    void add(User user);

    User get(Long id);

    List<User> listUsers();
}
