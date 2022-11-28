package explore.with.me.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import explore.with.me.locations.models.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Description:	Информация для редактирования события администратором. Все поля необязательные.
 * Значение полей не валидируется.
 *
 * @annotation String Краткое описание события
 * @category Long id категории к которой относится событие
 * @description String Полное описание события
 * @eventDate String Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
 * @location Location {@link Location} Широта и долгота места проведения события
 * @paid Boolean Нужно ли оплачивать участие в событии
 * @participantLimit Integer Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
 * @requestModeration Boolean Нужна ли пре-модерация заявок на участие
 * @title String Заголовок события
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUpdateEventRequest {
    private String title;
    private String annotation;
    private Long category;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
}
