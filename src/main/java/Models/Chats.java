package Models;

import java.util.Date;

public class Chats {
    private String speakToFirstName;
    private String speakToImage;
    private String currentText;
    private String speakToText;
    private Date messagesDateTime;

    public Chats() {
    }

    public void setSpeakToFirstName(String speakToFirstName) {
        this.speakToFirstName = speakToFirstName;
    }

    public void setSpeakToImage(String speakToImage) {
        this.speakToImage = speakToImage;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

    public void setSpeakToText(String speakToText) {
        this.speakToText = speakToText;
    }

    public void setMessagesDateTime(Date messagesDateTime) {
        this.messagesDateTime = messagesDateTime;
    }

    public String getSpeakToFirstName() {
        return speakToFirstName;
    }

    public String getSpeakToImage() {
        return speakToImage;
    }

    public String getCurrentText() {
        return currentText;
    }

    public String getSpeakToText() {
        return speakToText;
    }

    public Date getMessagesDateTime() {
        return messagesDateTime;
    }
}
