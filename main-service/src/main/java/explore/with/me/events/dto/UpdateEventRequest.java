package explore.with.me.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * description: Данные для изменения информации о событии
 * <p>
 *
 * @title String Новый заголовок
 * maxLength: 120
 * minLength: 3
 * @annotation string Новая аннотация
 * maxLength: 2000
 * minLength: 20
 * <p>
 * @category Long Новая категория
 * <p>
 * @description String Новое описание
 * maxLength: 7000
 * minLength: 20
 * <p>
 * @eventDate String Новые дата и время на которые намечено событие. Дата и время указываются
 * в формате "yyyy-MM-dd HH:mm:ss"
 * <p>
 * @eventId* Long Идентификатор события
 * <p>
 * @paid boolean Новое значение флага о платности мероприятия
 * <p>
 * @participantLimit Integer Новый лимит пользователей
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventRequest {
    @Length(min = 3, max = 120)
    private String title;
    @Length(min = 20, max = 2000)
    private String annotation;
    private Long category;
    @Length(min = 20, max = 7000)
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    @NotNull
    @Positive
    private Long eventId;
    private Boolean paid;
    private Integer participantLimit;
}
