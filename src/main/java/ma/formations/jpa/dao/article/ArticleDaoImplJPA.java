package ma.formations.jpa.dao.article;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ma.formations.jpa.dao.DatabaseManager;
import ma.formations.jpa.model.Article;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ArticleDaoImplJPA implements IArticleDao {
   private EntityManager session;

   @Override
   public List<Article> findAll() {
      List<Article> articles = new ArrayList<>();
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         articles = session.createQuery("from Article", Article.class).getResultList();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
      return articles;
   }

   @Override
   public void save(Article article) {
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         EntityTransaction tx = session.getTransaction();
         tx.begin();
         session.merge(article);
         tx.commit();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
   }

   @Override
   public void deleteAll() {
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         EntityTransaction tx = session.getTransaction();
         tx.begin();
         session.createQuery("delete from Article").executeUpdate();
         tx.commit();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
   }

   @Override
   public Article findById(Long id) {
      Article article = null;
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         article = session.find(Article.class, id);
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
      return article;
   }

   @Override
   public void delete(Long id) {
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         EntityTransaction tx = session.getTransaction();
         tx.begin();
         Article articleFound = session.find(Article.class, id);
         if (articleFound != null) {
            session.remove(articleFound);
         }
         tx.commit();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
   }

   @Override
   public void update(Article article) {
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         EntityTransaction tx = session.getTransaction();
         tx.begin();
         session.merge(article);
         tx.commit();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
   }

   @Override
   public List<Article> findByTitle(String title) {
      List<Article> articles = new ArrayList<>();
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         articles = session.createQuery("from Article where description = :title", Article.class)
                 .setParameter("title", title)
                 .getResultList();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
      return articles;
   }

   @Override
   public List<Article> findByAuthor(String author) {
      // Assuming 'author' field is added to the Article model
      // Adjust the query accordingly if the model is different
      List<Article> articles = new ArrayList<>();
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         articles = session.createQuery("from Article where author = :author", Article.class)
                 .setParameter("author", author)
                 .getResultList();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
      return articles;
   }

   @Override
   public List<Article> findByDateRange(LocalDate startDate, LocalDate endDate) {
      // Assuming 'date' field is added to the Article model
      // Adjust the query accordingly if the model is different
      List<Article> articles = new ArrayList<>();
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         articles = session.createQuery("from Article where date >= :startDate and date <= :endDate", Article.class)
                 .setParameter("startDate", startDate)
                 .setParameter("endDate", endDate)
                 .getResultList();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
      return articles;
   }

   @Override
   public long count() {
      long count = 0;
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         count = session.createQuery("select count(a) from Article a", Long.class)
                 .getSingleResult();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
      return count;
   }

   @Override
   public List<Article> findByKeyword(String keyword) {
      List<Article> articles = new ArrayList<>();
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         articles = session.createQuery("from Article where description like :keyword or content like :keyword", Article.class)
                 .setParameter("keyword", "%" + keyword + "%")
                 .getResultList();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
      return articles;
   }

   @Override
   public void saveAll(List<Article> articles) {
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         EntityTransaction tx = session.getTransaction();
         tx.begin();
         for (Article article : articles) {
            session.merge(article);
         }
         tx.commit();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
   }

   @Override
   public List<Article> findAll(int pageNumber, int pageSize) {
      List<Article> articles = new ArrayList<>();
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         articles = session.createQuery("from Article", Article.class)
                 .setFirstResult((pageNumber - 1) * pageSize)
                 .setMaxResults(pageSize)
                 .getResultList();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
      return articles;
   }

   @Override
   public List<Article> findByCategory(String category) {
      // Assuming 'category' field is added to the Article model
      // Adjust the query accordingly if the model is different
      List<Article> articles = new ArrayList<>();
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         articles = session.createQuery("from Article where category = :category", Article.class)
                 .setParameter("category", category)
                 .getResultList();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
      return articles;
   }

   @Override
   public List<Article> findMostRecent(int limit) {
      List<Article> articles = new ArrayList<>();
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         articles = session.createQuery("from Article order by date desc", Article.class)
                 .setMaxResults(limit)
                 .getResultList();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
      return articles;
   }

   @Override
   public Article getArticleById(int id) {
      Article article = null;
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         article = session.find(Article.class, (long) id);
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
       return article;
   }
}
