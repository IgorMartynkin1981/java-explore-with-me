package explore.with.me.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @id Идентификатор
 * @name* String Имя
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserShortDto {
    private Long id;
    private String name;

    public UserShortDto(String name) {
        this.name = name;
    }
}
