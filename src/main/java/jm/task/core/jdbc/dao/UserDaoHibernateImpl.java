package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    String sql;
    Session session = Util.getSessionFactory().openSession();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
       // Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        sql = "CREATE TABLE IF NOT EXISTS Users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
       // session.close();
    }

    @Override
    @Transactional
    public void dropUsersTable() {
        Transaction transaction = session.beginTransaction();
     //   Session session = Util.getSessionFactory().openSession();
        sql = "DROP TABLE IF EXISTS Users";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
      //  Session session = Util.getSessionFactory().openSession();
        User user = new User(name, lastName, age);
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
      //  session.close();
        System.out.println("User с именем - " + user.getName() + " " + user.getLastName() + " добавлен в базу данных.");
    }

    @Override
    public void removeUserById(long id) {
       // Session session = Util.getSessionFactory().openSession();
        User user;
        user = session.load(User.class, id);
        session.delete(user);
    //    session.close();
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>)  Util.getSessionFactory().openSession()
                .createQuery("From User")
                .list();
    }

    @Override
    public void cleanUsersTable() {
       // Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Users").executeUpdate();
        session.getTransaction().commit();
     //   session.close();
    }
}
