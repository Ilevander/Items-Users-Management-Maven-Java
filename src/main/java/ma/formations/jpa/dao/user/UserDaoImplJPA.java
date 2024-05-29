package ma.formations.jpa.dao.user;

import ma.formations.jpa.dao.DatabaseManager;
import ma.formations.jpa.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImplJPA implements IUserDao {
   private EntityManager session;

   @Override
   public User findById(Long id) {
      User user = null;
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         user = session.find(User.class, id);
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
      return user;
   }

   @Override
   public void delete(Long id) {
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         EntityTransaction tx = session.getTransaction();
         tx.begin();
         User userFound = session.find(User.class, id);
         if (userFound != null) {
            session.remove(userFound);
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
   public List<User> findAll() {
      List<User> users = new ArrayList<>();
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         users = session.createQuery("from User", User.class).getResultList();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
      return users;
   }

   @Override
   public void save(User user) {
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         EntityTransaction tx = session.getTransaction();
         tx.begin();
         session.merge(user);
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
   public void update(User user) {
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         EntityTransaction tx = session.getTransaction();
         tx.begin();
         session.merge(user);
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
   public User getUserByUsername(String username) {
      User user = null;
      try {
         session = DatabaseManager.getSessionFactory().createEntityManager();
         user = session.createQuery("from User where username = :username", User.class)
                 .setParameter("username", username)
                 .getSingleResult();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (session != null && session.isOpen()) {
            session.close();
         }
      }
      return user;
   }
}
