package main.dto.message;

import main.model.Message;

import java.time.format.DateTimeFormatter;

public class MessageMapper {

    public static DTOMessage map(Message message) {
        return new DTOMessage(message.getText(), message.getUser().getName(),
                message.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
