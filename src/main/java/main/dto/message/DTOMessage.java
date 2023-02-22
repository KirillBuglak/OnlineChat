package main.dto.message;

import java.time.LocalDateTime;

public class DTOMessage {

    private String text;
    private String username;
    private String datetime;

    public DTOMessage(String text, String username, String datetime) {
        this.text = text;
        this.username = username;
        this.datetime = datetime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
