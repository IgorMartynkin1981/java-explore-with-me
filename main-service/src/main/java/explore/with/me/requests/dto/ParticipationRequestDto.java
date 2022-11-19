package explore.with.me.requests.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * description:	 Заявка на участие в событии
 *
 * @id Long Идентификатор заявки
 * @created string Дата и время создания заявки
 * @event Long Идентификатор события
 * @requester Long Идентификатор пользователя, отправившего заявку
 * @status String Статус заявки
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationRequestDto {
    @NotNull
    @Positive
    private Long id;
    @NotNull
    @NotBlank
    private String created;
    @NotNull
    @Positive
    private Long event;
    private Long requester;
    private String status;
}
