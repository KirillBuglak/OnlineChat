package main;

import main.dto.message.DTOMessage;
import main.dto.message.MessageMapper;
import main.dto.user.DTOUser;
import main.dto.user.UserMapper;
import main.model.Message;
import main.model.User;
import main.repositories.MessageRepository;
import main.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ChatService {
    private final UserRepository uRep;
    private final MessageRepository mRep;

    public ChatService(UserRepository uRep, MessageRepository mRep) {
        this.uRep = uRep;
        this.mRep = mRep;
    }

    public List<DTOUser> userResult() {
        List<DTOUser> users = new ArrayList<>();
        uRep.findAll().forEach(e -> users.add(UserMapper.map(e)));
        return users;
    }

    public List<DTOMessage> getMResult() {
        List<DTOMessage> messages = new ArrayList<>();
        mRep.findAll().forEach(e -> messages.add(MessageMapper.map(e)));
        return messages;
    }

    public HashMap<String, Boolean> postMResult(String text) {
        HashMap<String, Boolean> response = new HashMap<>();
        response.put("result", false);
        if (!text.isEmpty()) {
            response.clear();
            response.put("result", true);
        }
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        Message message = new Message();
        message.setText(text);
        message.setDateTime(LocalDateTime.now());
        AtomicReference<User> user = settingUser(sessionId);
        message.setUser(user.get());
        mRep.save(message);
        return response;
    }

    public HashMap<String, Boolean> authResult(String name) {
        HashMap<String, Boolean> response = new HashMap<>();
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        User user = new User();
        user.setName(name);
        user.setSessionId(sessionId);
        uRep.save(user);
        response.put("result", true);
        return response;
    }

    public HashMap<String, Boolean> initResult() {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        HashMap<String, Boolean> response = new HashMap<>();
        response.put("result", false);
        checkAuth(sessionId, response);
        return response;
    }

    public void checkAuth(String sessionId, HashMap<String, Boolean> response) {
        uRep.findAll().forEach(e -> {
            if (e.getSessionId().equals(sessionId)) {
                response.clear();
                response.put("result", true);
            }
        });
    }

    public AtomicReference<User> settingUser(String sessionId) {
        AtomicReference<User> user = new AtomicReference<>();
        uRep.findAll().forEach(e ->
        {
            if (e.getSessionId().equals(sessionId)) {
                user.set(e);
            }
        });
        return user;
    }
}
