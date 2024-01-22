package app.dao;

import app.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getAllUsers();

    Optional<User> findById(Long id);

    void save(User user);

    User update(User user);

    boolean deleteById(Long id);
}
