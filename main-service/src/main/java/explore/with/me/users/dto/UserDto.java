package explore.with.me.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * description:	 Пользователь
 *
 * @id Идентификатор
 * @email* String Почтовый адрес
 * @name* String Имя
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Positive
    @NotNull
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @Email
    @NotNull
    private String email;

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
