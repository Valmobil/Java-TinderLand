package models;

import java.util.UUID;

public class Logins {
    private UUID loginsId; //link to UsersId
    private String loginsEMail;
    private String password;

    public UUID getLoginsId() {
        return loginsId;
    }

    public void setLoginsId(UUID loginsId) {
        this.loginsId = loginsId;
    }

    public String getLoginsEMail() {
        return loginsEMail;
    }

    public void setLoginsEMail(String loginsEMail) {
        this.loginsEMail = loginsEMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
