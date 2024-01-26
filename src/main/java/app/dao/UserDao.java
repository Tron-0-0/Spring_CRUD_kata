package app.dao;

import app.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getAllUsers();

    User findById(Long id);

    void save(User user);

    void update(User user);

    void deleteById(Long id);
}
