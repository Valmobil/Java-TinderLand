package Models;

import java.util.UUID;

public class Likes {
    private UUID likesCurrentUserId;
    private UUID likesLikedUserId;
    private String linkesValue;

    public Likes(UUID likesCurrentUser, UUID likesLikedUserId, String linkesValue) {
        this.likesCurrentUserId = likesCurrentUser;
        this.likesLikedUserId = likesLikedUserId;
        this.linkesValue = linkesValue;
    }

    public UUID getLikesCurrentUserId() {
        return likesCurrentUserId;
    }

    public void setLikesCurrentUserId(UUID likesCurrentUserId) {
        this.likesCurrentUserId = likesCurrentUserId;
    }

    public UUID getLikesLikedUserId() {
        return likesLikedUserId;
    }

    public void setLikesLikedUserId(UUID likesLikedUserId) {
        this.likesLikedUserId = likesLikedUserId;
    }

    public String getLinkesValue() {
        return linkesValue;
    }

    public void setLinkesValue(String linkesValue) {
        this.linkesValue = linkesValue;
    }
}
