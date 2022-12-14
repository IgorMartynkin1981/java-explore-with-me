package explore.with.me.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import explore.with.me.locations.models.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * description:	 Новое событие
 *
 * @annotation* String Краткое описание события
 * maxLength: 2000
 * minLength: 20
 * @category* Long id категории к которой относится событие
 * @description* String Полное описание события
 * maxLength: 7000
 * minLength: 20
 * @eventDate* String Дата и время на которые намечено событие. Дата и время указываются в формате "yyyy-MM-dd HH:mm:ss"
 * @location* Location {@link Location} Широта и долгота места проведения события
 * @paid Boolean Нужно ли оплачивать участие в событии
 * default: false
 * @participantLimit Integer Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
 * default: 0
 * @requestModeration Boolean Нужна ли пре-модерация заявок на участие. Если true, то все заявки будут
 * ожидать подтверждения инициатором события. Если false - то будут подтверждаться автоматически.
 * default: true
 * @title* String Заголовок события
 * maxLength: 120
 * minLength: 3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewEventDto {

    @NotNull
    @NotBlank
    @Length(min = 3, max = 120)
    private String title;
    @NotBlank
    @NotNull
    @Length(min = 20, max = 2000)
    private String annotation;
    @NotNull
    @Positive
    private Long category;
    @NotBlank
    @NotNull
    @Length(min = 20, max = 7000)
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private @NotNull @Future LocalDateTime eventDate;
    @NotNull
    private Location location;
    private Boolean paid;
    @PositiveOrZero
    private Integer participantLimit;
    private Boolean requestModeration;
}
