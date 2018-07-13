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

public class ChatDAO implements InterfaceDAO<Chats> {
    private UUID currentUser;

    public void setCurrentUser(UUID currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void insert(Chats obj) { throw new IllegalArgumentException("not implemented"); }

    @Override
    public void update(Chats messages) {
        throw new IllegalArgumentException("not implemented");
    }

    @Override
    public List<Chats> get(String filter) {
        List<Chats> items = new ArrayList<>();

        String sql =
                "  SELECT " +
                        "  CASE WHEN m.messagesuserfromid <> ? THEN u.usersfirstname ELSE '' END as speakToFirstName," +
                        "  CASE WHEN m.messagesuserfromid <> ? THEN u.userslinkphoto ELSE '' END as speakToImage," +
                        "  CASE WHEN m.messagesuserfromid = ? THEN m.messagetext ELSE '' END as currentuserText," +
                        "  CASE WHEN m.messagesuserfromid <> ? THEN m.messagetext ELSE '' END as speakToText," +
                        "  m.messagesdatetime as messagesDateTime" +
                        "  FROM messages as m" +
                        "  INNER JOIN users as u ON m.messagesuserfromid = u.usersid";
        if (filter.length() > 0) {
            sql += " where " + filter;
        }

        try (
                Connection connection = ConnectionToDB.getConnection()
        ) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, this.currentUser);
            statement.setObject(2, this.currentUser);
            statement.setObject(3, this.currentUser);
            statement.setObject(4, this.currentUser);

            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                Chats chats = new Chats();
                chats.setMessagesDateTime(rSet.getDate("messagesdatetime"));
                chats.setCurrentText(rSet.getString("currentusertext"));
                chats.setSpeakToFirstName(rSet.getString("speaktofirstname"));
                chats.setSpeakToImage(rSet.getString("speaktoimage"));
                chats.setSpeakToText(rSet.getString("speaktotext"));
                items.add(chats);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public void delete(Chats messages) {
        throw new IllegalArgumentException("not implemented");
    }
}
