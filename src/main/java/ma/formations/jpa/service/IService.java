package ma.formations.jpa.service;

import ma.formations.jpa.model.Article;

import java.util.List;

public interface IService {
    Boolean checkAccount(String username,String password);
    List<Article> getAllArticle();

    Article findArticleById(Long id);

    void updateArticle(Article article);
}
