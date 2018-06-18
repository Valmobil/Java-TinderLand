package Models;

import java.util.Date;
import java.util.UUID;

public class Users {
    private UUID userId;
    private String userFirstName;
    private String userPosition;
    private String userLinkPhoto;
    private Date userLastLogin;

    public Users(String userFirstName, String userPosition, String userLinkPhoto) {
        this.setUserId();
        this.userFirstName = userFirstName;
        this.userPosition = userPosition;
        this.userLinkPhoto = userLinkPhoto;
        this.userLastLogin = userLastLogin;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId() {
        this.userId = UUID.randomUUID();
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLinkPhoto() {
        return userLinkPhoto;
    }

    public void setUserLinkPhoto(String userLinkPhoto) {
        this.userLinkPhoto = userLinkPhoto;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public Date getUserLastLogin() {
        return userLastLogin;
    }

    public void setUserLastLogin(Date userLastLogin) {
        this.userLastLogin = userLastLogin;
    }
}
