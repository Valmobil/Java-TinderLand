package Models;

import java.util.Date;
import java.util.UUID;

public class Messages {
    UUID messagesId;
    UUID messagesuserfromid;
    UUID messagesusertoid;
    String messagestext;
    Date messagesDateTime;

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

    public Date getMessagesDateTime() {
        return messagesDateTime;
    }

    public void setMessagesDateTime(Date messagesDateTime) {
        this.messagesDateTime = messagesDateTime;
    }
}
