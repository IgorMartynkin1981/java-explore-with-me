package explore.with.me.users.services;

import explore.with.me.users.dto.NewUserRequest;
import explore.with.me.users.dto.UserDto;
import explore.with.me.users.models.User;

import java.util.Collection;
import java.util.List;

/**
 * Контракт на пользователя
 *
 * @getUsers получить коллекцию пользователей
 * @addUser добавить и записать пользователя в БД
 * @deleteUser удалить пользователя из БД
 * @getUserById получить пользователя по id из БД
 */
public interface UserService {

    Collection<UserDto> getUsers(List<Long> usersIds, Integer from, Integer size);

    UserDto addUser(NewUserRequest newUserRequest);

    void deleteUser(Long userId);

    User getUserById(Long userId);
}
