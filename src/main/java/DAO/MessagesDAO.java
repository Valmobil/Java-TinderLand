package DAO;

import Models.Chats;
import Models.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessagesDAO implements InterfaceDAO<Messages> {
    private UUID currentUser;

    public void setCurrentUser(UUID currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void insert(Messages messages) {
        String sql = "INSERT INTO messages (messagesid, messagesuserfromid, messagesusertoid, messagetext, messagesdatetime) VALUES (?,?,?,?,?)";

        Connection connection = ConnectionToDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, messages.getMessagesId() );
            statement.setObject(2, messages.getMessagesuserfromid());
            statement.setObject(3, messages.getMessagesusertoid());
            statement.setString(4, messages.getMessagesText());
            statement.setObject(5, messages.getMessagesDateTime());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Messages messages) {
        String sql = "UPDATE mESSAGES SET messagesid = ?, messagesuserfromid = ?, messagesusertoid = ?, messagesdatetime = ? WHERE messagesid = ?";
        Connection connection = ConnectionToDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, messages.getMessagesuserfromid());
            statement.setObject(2, messages.getMessagesusertoid());
            statement.setString(3, messages.getMessagesText());
            statement.setObject(4, messages.getMessagesDateTime());
            statement.setObject(5, messages.getMessagesId() );

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Messages> get(String filter) {
        throw new IllegalArgumentException("not implemented");
    }


    @Override
    public void delete(Messages messages) {
        throw new IllegalArgumentException("not implemented");
    }


}
