package explore.with.me.requests.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * Description:	Заявка на участие в событии
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private @NotNull LocalDateTime created;
    @NotNull
    @Positive
    private Long event;
    private Long requester;
    private String status;
}
