package models;

import java.util.UUID;

public class Likes {
    private UUID likesId;
    private UUID likesCurrentUserId;
    private UUID likesLikedUserId;
    private String linkesValue;

    public Likes(UUID likesid, UUID likesCurrentUser, UUID likesLikedUserId, String linkesValue) {
        this.likesId = likesId;
        this.likesCurrentUserId = likesCurrentUser;
        this.likesLikedUserId = likesLikedUserId;
        this.linkesValue = linkesValue;
    }

    public Likes(UUID likesCurrentUser, UUID likesLikedUserId, String linkesValue) {
        this.setLikesId();
        this.likesCurrentUserId = likesCurrentUser;
        this.likesLikedUserId = likesLikedUserId;
        this.linkesValue = linkesValue;
    }

    public Likes() {
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

    public UUID getLikesId() {
        return likesId;
    }

    public void setLikesId() {
        this.likesId = UUID.randomUUID();
    }

    public void setLikesId(UUID likesId) {
        this.likesId = likesId;
    }
}
