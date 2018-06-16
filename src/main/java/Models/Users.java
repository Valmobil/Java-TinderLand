package Models;

import java.util.UUID;

public class Users {
    UUID userId;
    String userFirstName;
    String userLinkPhoto;

    public Users(UUID userId, String userFirstName, String userLinkPhoto) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLinkPhoto = userLinkPhoto;
    }

    public Users(String userFirstName, String userLinkPhoto) {
        this.setUserId();
        this.userFirstName = userFirstName;
        this.userLinkPhoto = userLinkPhoto;
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
}
