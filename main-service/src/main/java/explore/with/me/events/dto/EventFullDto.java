package explore.with.me.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import explore.with.me.categories.dto.CategoryDto;
import explore.with.me.categories.models.Category;
import explore.with.me.events.models.State;
import explore.with.me.locations.models.Location;
import explore.with.me.users.dto.UserShortDto;
import explore.with.me.users.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

/**
 * @id Long Идентификатор
 * @title* String Заголовок
 * @annotation* String Краткое описание
 * @category* CategoryDto {@link Category}  Категория
 * @confirmedRequests Количество одобренных заявок на участие в данном событии
 * @createdOn String Дата и время создания события (в формате "yyyy-MM-dd HH:mm:ss")
 * @description String Полное описание события
 * @eventDate* String Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
 * @initiator* UserShortDto {@link User} Пользователь (краткая информация)
 * @location* Location {@link Location} Широта и долгота места проведения события
 * @paid* boolean Нужно ли оплачивать участие
 * @participantLimit Integer Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
 * @publishedOn String Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss")
 * @requestModeration boolean {default: true} Нужна ли пре-модерация заявок на участие
 * @state String {@link State} Список состояний жизненного цикла события
 * @views integer Количество просмотрев события
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventFullDto extends EventShortDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
    private String description;
    @NotNull
    @NotBlank
    private Location location;
    private Integer participantLimit;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private State state;

    public EventFullDto(@Positive Long id, @NotBlank String title,
                        @NotBlank String annotation, @NotNull @NotBlank CategoryDto category,
                        @PositiveOrZero Integer confirmedRequests, LocalDateTime eventDate,
                        @NotNull @NotBlank UserShortDto initiator, @NotNull @NotBlank Boolean paid,
                        Integer views, LocalDateTime createdOn, String description, Location location,
                        Integer participantLimit, Boolean requestModeration,
                        State state) {
        super(id, title, annotation, category, confirmedRequests, eventDate, initiator, paid, views);
        this.createdOn = createdOn;
        this.description = description;
        this.location = location;
        this.participantLimit = participantLimit;
        this.requestModeration = requestModeration;
        this.state = state;
    }
}
