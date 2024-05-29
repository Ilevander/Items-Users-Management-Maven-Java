package ma.formations.jpa.dao.user;

import ma.formations.jpa.model.User;

import java.util.List;

public interface IUserDao {
    User findById(Long id);
    void delete(Long id);
    List<User> findAll();
    void save(User user);
    void update(User user);
    User getUserByUsername(String username);
}
