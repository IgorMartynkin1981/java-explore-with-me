package explore.with.me.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Пользователь
 */

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
}
