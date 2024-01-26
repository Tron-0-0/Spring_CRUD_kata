package app.service;

import app.dao.UserDaoImpl;
import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserDaoImpl userDao;

    @Autowired
    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User findById(Long id) {
        return userDao.findById(id);
    }

    public void save(User user) {
        userDao.save(user);
    }
    
    public void update(User user) {
        userDao.update(user);
    }
    
    public void delete(Long id) {
        userDao.deleteById(id);
    }

}
