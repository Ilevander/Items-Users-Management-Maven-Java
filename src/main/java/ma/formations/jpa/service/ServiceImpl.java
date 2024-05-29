package ma.formations.jpa.service;

import ma.formations.jpa.dao.DaoImplJPA;
import ma.formations.jpa.dao.IDao;
import ma.formations.jpa.dao.article.ArticleDaoImplJPA;
import ma.formations.jpa.dao.article.IArticleDao;
import ma.formations.jpa.model.Article;
import ma.formations.jpa.model.User;

import java.util.List;

public class ServiceImpl implements IService {
    private IDao dao = new DaoImplJPA();
    private IArticleDao daoArticle = new ArticleDaoImplJPA();

    @Override
    public Boolean checkAccount(String username, String password) {
        User u = dao.getUserByUsername(username);
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
}
