package main.controllers;

import main.ChatService;
import main.dto.message.DTOMessage;
import main.dto.user.DTOUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/init")
    public HashMap<String, Boolean> init() {
        return chatService.initResult();
    }

    @PostMapping("/auth")
    public HashMap<String, Boolean> auth(@Valid @RequestParam String name) {
        return chatService.authResult(name);
    }

    @PostMapping("/message")
    public HashMap<String, Boolean> sendMessage(@RequestParam String text) {
        return chatService.postMResult(text);
    }

    @GetMapping("/message")
    public List<DTOMessage> getMessagesList() {
        return chatService.getMResult();
    }

    @GetMapping("/user")
    public List<DTOUser> getUsersList() {
        return chatService.userResult();
    }
}
