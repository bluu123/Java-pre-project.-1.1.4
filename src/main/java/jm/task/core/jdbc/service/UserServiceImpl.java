package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl daoJDBC;
    {
        try {
            daoJDBC = new UserDaoJDBCImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    UserDaoHibernateImpl daoHibernate = new UserDaoHibernateImpl();


    public void createUsersTable() {
        try {
            daoJDBC.createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            daoJDBC.dropUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            daoJDBC.saveUser(name, lastName, age);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            daoJDBC.removeUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try {
            return daoJDBC.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try {
            daoJDBC.cleanUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createUsersTableHibernate() {
        daoHibernate.createUsersTable();
    }
    public void dropUsersTableHibernate() {
        daoHibernate.dropUsersTable();
    }
    public void saveUserHibernate(String name, String lastName, byte age) {
            daoHibernate.saveUser(name, lastName, age);
    }
    public void removeUserByIdHibernate(long id) {
        try {
            daoJDBC.removeUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> getAllUsersHibernate() {
        return daoHibernate.getAllUsers();
    }
    public void cleanUsersTableHibernate() {
        try {
            daoJDBC.cleanUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

