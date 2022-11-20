package explore.with.me.users.services;

import explore.with.me.users.dto.NewUserRequest;
import explore.with.me.users.dto.UserDto;
import explore.with.me.users.models.User;

import java.util.Collection;
import java.util.List;

public interface UserService {

    Collection<UserDto> getUsers(List<Long> usersIds, Integer from, Integer size);

    UserDto addUser(NewUserRequest newUserRequest);

    void deleteUser(Long userId);


}
