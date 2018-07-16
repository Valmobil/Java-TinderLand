package models;

import java.sql.Timestamp;
import java.util.UUID;

public class Messages {
    private UUID messagesId;
    private UUID messagesuserfromid;
    private UUID messagesusertoid;
    private String messagestext;
    private Timestamp messagesDateTime;

    public Messages(UUID messagesuserfromid, UUID messagesusertoid, String messagestext, Timestamp messagesDateTime) {
        this.messagesId = UUID.randomUUID();
        this.messagesuserfromid = messagesuserfromid;
        this.messagesusertoid = messagesusertoid;
        this.messagestext = messagestext;
        this.messagesDateTime = messagesDateTime;
    }

    public UUID getMessagesId() {
        return messagesId;
    }

    public void setMessagesId(UUID messagesId) {
        this.messagesId = messagesId;
    }

    public UUID getMessagesuserfromid() {
        return messagesuserfromid;
    }

    public void setMessagesuserfromid(UUID messagesuserfromid) {
        this.messagesuserfromid = messagesuserfromid;
    }

    public UUID getMessagesusertoid() {
        return messagesusertoid;
    }

    public void setMessagesusertoid(UUID messagesusertoid) {
        this.messagesusertoid = messagesusertoid;
    }

    public String getMessagesText() {
        return messagestext;
    }

    public void setMessagestext(String messagestext) {
        this.messagestext = messagestext;
    }

    public Timestamp getMessagesDateTime() {
        return messagesDateTime;
    }

    public void setMessagesDateTime(Timestamp messagesDateTime) {
        this.messagesDateTime = messagesDateTime;
    }
}
