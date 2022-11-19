package explore.with.me.events.dto;

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
public class EventFullDto {
    @Positive
    private Long id;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String annotation;
    @NotNull
    @NotBlank
    private CategoryDto category;
    private Integer confirmedRequests;
    private String createdOn;
    private String description;
    @NotNull
    @NotBlank
    private String eventDate;
    @NotNull
    @NotBlank
    private UserShortDto initiator;
    @NotNull
    @NotBlank
    private Location location;
    @NotNull
    @NotBlank
    private Boolean paid;
    private Integer participantLimit;
    private String publishedOn;
    private Boolean requestModeration;
    private State state;
    private Integer views;

    public EventFullDto(Long id, String title, String annotation, CategoryDto category,
                        Integer confirmedRequests, String createdOn, String description,
                        String eventDate, UserShortDto initiator, Location location,
                        Boolean paid, Integer participantLimit, Boolean requestModeration,
                        State state, Integer views) {
        this.id = id;
        this.title = title;
        this.annotation = annotation;
        this.category = category;
        this.confirmedRequests = confirmedRequests;
        this.createdOn = createdOn;
        this.description = description;
        this.eventDate = eventDate;
        this.initiator = initiator;
        this.location = location;
        this.paid = paid;
        this.participantLimit = participantLimit;
        this.requestModeration = requestModeration;
        this.state = state;
        this.views = views;
    }
}
