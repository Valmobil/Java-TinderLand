package DAO;

import models.Likes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class LikesDAO implements InterfaceDAO<Likes> {

    @Override
    public void insert(Likes likes) {
        String sql = "INSERT INTO likes(likesid, likescurrentuserid, likeslikeduserid, likesvalue) VALUES(?,?,?,?)";

        try (Connection connection = (Connection) ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setObject(1, likes.getLikesId());
            statement.setObject(2, likes.getLikesCurrentUserId());
            statement.setObject(3, likes.getLikesLikedUserId());
            statement.setString(4, likes.getLinkesValue());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Likes likes) {
        String sql = "UPDATE likes SET likescurrentuserid=?, likeslikeduserid=?,likesvalue=? WHERE likesid=?";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, String.valueOf(likes.getLikesCurrentUserId()));
            statement.setString(2, String.valueOf(likes.getLikesLikedUserId()));
            statement.setString(3, likes.getLinkesValue());
            statement.setString(5, String.valueOf(likes.getLikesId()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Likes> get(String filter) {
        {
            List<Likes> items = new ArrayList<>();

            String sql = "SELECT * FROM likes";
            if (filter.length() > 0) {
                sql += " where " + filter;
            }

            try (
                    Connection connection = ConnectionToDB.getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql);

            ) {
                ResultSet rSet = statement.executeQuery();
                while (rSet.next()) {
                    Likes likes = new Likes();
                    likes.setLikesId(UUID.fromString(rSet.getString("likesid")));
                    likes.setLikesCurrentUserId(UUID.fromString(rSet.getString("likescurrentuserid")));
                    likes.setLikesLikedUserId(UUID.fromString(rSet.getString("likeslikeduserid")));
                    likes.setLinkesValue(rSet.getString("likesvalue"));

                    items.add(likes);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return items;

        }
    }

    @Override
    public void delete(Likes like) {
        String sql = "DELETE FROM likes WHERE likesid=?";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, String.valueOf(like.getLikesId()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(UUID currentUser) {
        String sql = "DELETE FROM likes where likescurrentuserid = '" + currentUser + "'";
        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

