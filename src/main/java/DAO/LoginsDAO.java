package DAO;

import Models.Chats;
import Models.Logins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoginsDAO {



    public UUID getFromDB(String loginsemail, String loginspassword) {
        //UUID currentuser = UUID.randomUUID();
        UUID newCurrentUser =  null;
        String sql =
                "  select * from logins" +
                        "  where loginsemail = ?";
        try (
                Connection connection = ConnectionToDB.getConnection();
        ) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, loginsemail);

            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                Logins logins = new Logins();
                //logins.setLoginsEMail(rSet.getString("loginsemail"));
                logins.setLoginsId((UUID) rSet.getObject("loginsid"));
                return logins.getLoginsId();
            } else {
                sql = "INSERT INTO logins (loginsid, loginsemail, loginspassword) VALUES (?,?,?)";
                try {
                    statement = connection.prepareStatement(sql);
                    newCurrentUser = UUID.randomUUID();
                    statement.setObject(1, newCurrentUser );
                    statement.setObject(2, loginsemail);
                    statement.setObject(3, loginspassword);

                    statement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newCurrentUser;
    }
}
