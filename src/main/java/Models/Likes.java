package Models;

import java.util.UUID;

public class Likes {
    UUID likesCurrentUser;
    UUID likesLikedUser;
    String linkesValue;

    public Likes(UUID likesCurrentUser, UUID likesLikedUser, String linkesValue) {
        this.likesCurrentUser = likesCurrentUser;
        this.likesLikedUser = likesLikedUser;
        this.linkesValue = linkesValue;
    }
}
