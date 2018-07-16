package DAO;

import models.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class UsersDAO implements InterfaceDAO<Users> {

    @Override
    public void insert(Users user) {
        String sql = "INSERT INTO users(usersid, usersfirtname, usersposition, userslinkphoto, userlastlogin) VALUES(?,?,?,?,?)";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(user.getUserId()));
            statement.setString(2, user.getUserFirstName());
            statement.setString(3, user.getUserPosition());
            statement.setString(4, user.getUserLinkPhoto());
            statement.setString(5, user.getUserLinkPhoto());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Users user) {
        String sql = "UPDATE users SET usersfirtname=?, usersposition=?, userslinkphoto=?, userlastlogin=? WHERE usersid=?";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(5, String.valueOf(user.getUserId()));
            statement.setString(1, user.getUserFirstName());
            statement.setString(2, user.getUserPosition());
            statement.setString(3, user.getUserLinkPhoto());
            statement.setString(4, user.getUserLinkPhoto());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Users> get(String filter) {
        {
            List<Users> items = new ArrayList<>();

            String sql = "SELECT * FROM users";
            if (filter.length() > 0) {
                sql += " where " + filter;
            }

            try (
                    Connection connection = ConnectionToDB.getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)

            ) {
                ResultSet rSet = statement.executeQuery();
                while (rSet.next()) {
                    Users user = new Users();
                    //usersid, usersfirtname, usersposition, userslinkphoto, userlastlogin
                    user.setUserId(UUID.fromString(rSet.getString("usersid")));
                    user.setUserFirstName(rSet.getString("usersfirstname"));
                    user.setUserPosition(rSet.getString("usersposition"));
                    user.setUserLinkPhoto(rSet.getString("userslinkphoto"));
                    user.setUserLastLogin(rSet.getDate("userslastlogin"));

                    items.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return items;
        }
    }

    @Override
    public void delete(Users users) {
        String sql = "DELETE FROM users WHERE usersid=?";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, String.valueOf(users.getUserId()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}