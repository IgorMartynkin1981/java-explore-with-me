package explore.with.me.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Пользователь (краткая информация)
 */

@Data
@AllArgsConstructor
public class UserShortDto {
    private Long id;
    private String name;
}
