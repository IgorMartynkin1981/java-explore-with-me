package explore.with.me.users.services;

import explore.with.me.users.dto.NewUserRequest;
import explore.with.me.users.dto.UserDto;
import explore.with.me.users.models.User;
import explore.with.me.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<UserDto> getUsers(List<Long> usersIds, Integer from, Integer size) {
        return null;
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public UserDto addUser(NewUserRequest newUserRequest) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
