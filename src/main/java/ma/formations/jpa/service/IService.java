package ma.formations.jpa.service;

import ma.formations.jpa.model.Article;
import ma.formations.jpa.model.User;

import java.util.List;

public interface IService {
    Boolean checkAccount(String username,String password);
    List<Article> getAllArticle();

    Article findArticleById(Long id);

    void updateArticle(Article article);

    void deleteArticle(Long id);

    boolean addArticle(Article article);

    Article getArticleById(int id);

    List<User> getAllUsers();

    User getUserById(int id);

    void updateUser(User user);

    void deleteUser(long id);

    void addUser(User newUser);
}
