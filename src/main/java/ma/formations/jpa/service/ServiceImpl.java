package ma.formations.jpa.service;

import ma.formations.jpa.dao.IDao;
import ma.formations.jpa.dao.DaoImplJPA;
import ma.formations.jpa.dao.article.IArticleDao;
import ma.formations.jpa.dao.article.ArticleDaoImplJPA;
import ma.formations.jpa.dao.user.IUserDao;
import ma.formations.jpa.dao.user.UserDaoImplJPA;
import ma.formations.jpa.model.Article;
import ma.formations.jpa.model.User;

import java.util.List;

public class ServiceImpl implements IService {
    private IDao dao = new DaoImplJPA();
    private IArticleDao daoArticle = new ArticleDaoImplJPA();
    private IUserDao userDao = new UserDaoImplJPA();

    @Override
    public Boolean checkAccount(String username, String password) {
        User u = userDao.getUserByUsername(username);
        if (u == null)
            return false;
        return password.equals(u.getPassword());
    }

    @Override
    public List<Article> getAllArticle() {
        return daoArticle.findAll();
    }

    @Override
    public Article findArticleById(Long id) {
        return daoArticle.findById(id);
    }

    @Override
    public void updateArticle(Article article) {
        daoArticle.update(article);
    }

    @Override
    public void deleteArticle(Long id) {
        daoArticle.delete(id);
    }

    @Override
    public boolean addArticle(Article article) {
        try {
            daoArticle.save(article);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Article getArticleById(int id) {
        return daoArticle.getArticleById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userDao.findById((long) id);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.delete(id);
    }

    @Override
    public void addUser(User newUser) {
        userDao.save(newUser);
    }
}
