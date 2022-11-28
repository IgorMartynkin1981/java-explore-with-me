package explore.with.me.users.dto;

import explore.with.me.users.models.User;

public class UserMapper {

    public static User toUser(NewUserRequest newUserRequest, Long userId) {
        if (userId == null) {
            return new User(newUserRequest.getName(), newUserRequest.getEmail());
        } else {
            return new User(userId, newUserRequest.getName(), newUserRequest.getEmail());
        }
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    public static UserShortDto toUserShortDto(User user) {
        return new UserShortDto(user.getId(), user.getName());
    }
}
