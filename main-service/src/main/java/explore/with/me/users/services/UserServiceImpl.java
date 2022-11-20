package explore.with.me.users.services;

import explore.with.me.exeption.BadRequestException;
import explore.with.me.users.dto.NewUserRequest;
import explore.with.me.users.dto.UserDto;
import explore.with.me.users.dto.UserMapper;
import explore.with.me.users.models.User;
import explore.with.me.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<UserDto> getUsers(List<Long> usersIds, Integer from, Integer size) {
        if (usersIds == null) {
            {
                if (from < 0 || size < 1) {
                    throw new BadRequestException("No positive values in the pagination");
                }
            }
            int page = from / size;
            PageRequest pageRequest = PageRequest.of(page, size, Sort.by("name"));
            return userRepository.findAll(pageRequest)
                    .stream().map(UserMapper::toUserDto).collect(Collectors.toList());
        } else {
            return userRepository.findAllById(usersIds)
                    .stream().map(UserMapper::toUserDto).collect(Collectors.toList());
        }
    }

    @Override
    public UserDto addUser(NewUserRequest newUserRequest) {
        User user = userRepository.save(UserMapper.toUser(newUserRequest, null));
        return UserMapper.toUserDto(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
