package main.dto.user;

import main.model.User;

public class UserMapper {
    public static DTOUser map(User user) {
        return new DTOUser(String.valueOf(user.getId()), user.getName());
    }
}
