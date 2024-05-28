package ma.formations.jpa.dao.article;

import java.time.LocalDate;
import java.util.List;
import ma.formations.jpa.model.Article;

public interface IArticleDao {
    Article findById(Long id);
    void delete(Long id);
    List<Article> findAll();
    void save(Article article);
    void deleteAll();
    void update(Article article);
    List<Article> findByTitle(String title);
    List<Article> findByAuthor(String author);
    List<Article> findByDateRange(LocalDate startDate, LocalDate endDate);
    long count();
    List<Article> findByKeyword(String keyword);
    void saveAll(List<Article> articles);
    List<Article> findAll(int pageNumber, int pageSize);
    List<Article> findByCategory(String category);
    List<Article> findMostRecent(int limit);
}
