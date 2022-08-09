package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection conn = Util.getMySQLConnection("127.0.0.1", "mysql", "root", "12345");


    public UserDaoJDBCImpl() throws SQLException {

    }

    public void createUsersTable() throws SQLException {
        DatabaseMetaData md = conn.getMetaData();
        ResultSet resultSet = md.getTables(null, null, "Users", null);
        resultSet.next();
        if (!resultSet.next()) {
            String usersTable = "CREATE TABLE Users ("
                    + "id INT NOT NULL AUTO_INCREMENT,"
                    + "name VARCHAR(45) NOT NULL,"
                    + "lastName VARCHAR(45) NOT NULL,"
                    + "age INT NOT NULL,"
                    + "PRIMARY KEY (id))"
                    + "ENGINE = InnoDB AUTO_INCREMENT = 1";
            conn.createStatement()
                    .executeUpdate(usersTable);
        }else {
            System.out.println("Таблица уже существует");
        }
    }

    public void dropUsersTable() throws SQLException {
        DatabaseMetaData md = conn.getMetaData();
        ResultSet resultSet = md.getTables(null, null, "Users", null);
        resultSet.next();
        if (resultSet.next()) {
            Statement stmt = conn.createStatement();
            String query = "DROP TABLE Users";
            stmt.executeUpdate(query);
        } else {
            System.out.println("Таблица уже удалена");
        }
     }


    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO Users(name, lastName, age) VALUES (" + "'" + name
                + "'" + ", " + "'" + lastName + "'" + ", " + age + ")");
        System.out.println("User с именем - " + name + " " + lastName + " " + "добавлен в базу данных.");
    }

    public void removeUserById(long id) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM Users WHERE ID = " + id);
    }

    public List<User> getAllUsers() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM Users");
        List<User> list = new ArrayList<>();
        while(resultSet.next()){
            User user = new User(resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getByte(4));
            user.setId(resultSet.getLong(1));
            list.add(user);
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        Connection conn = Util.getMySQLConnection("127.0.0.1", "mysql", "root", "12345");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM Users");
    }
}
