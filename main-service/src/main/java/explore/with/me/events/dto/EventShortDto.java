package explore.with.me.events.dto;

import explore.with.me.categories.dto.CategoryDto;
import explore.with.me.categories.models.Category;
import explore.with.me.users.dto.UserShortDto;
import explore.with.me.users.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * description: Краткая информация о событии
 *
 * @id Long Идентификатор
 * @title* String Заголовок
 * @annotation* String Краткое описание
 * @category* CategoryDto {@link Category}  Категория
 * @confirmedRequests Количество одобренных заявок на участие в данном событии
 * @eventDate* String Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
 * @initiator* UserShortDto {@link User} Пользователь (краткая информация)
 * @paid* boolean Нужно ли оплачивать участие
 * @views integer Количество просмотрев события
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventShortDto {
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
    @NotNull
    @NotBlank
    private String eventDate;
    @NotNull
    @NotBlank
    private UserShortDto initiator;
    @NotNull
    @NotBlank
    private Boolean paid;
    private Integer views;
}
