package explore.with.me.compilations.dto;

import explore.with.me.events.dto.EventShortDto;
import explore.with.me.events.models.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * Подборка событий
 *
 * @id* [...]
 * @title* String Заголовок подборки
 * @events    {@link Event} Список событий входящих в подборку
 * @pinned* boolean Закреплена ли подборка на главной странице сайта
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompilationDto {
    @Positive @NotNull @NotBlank
    private Long id;
    @NotNull @NotBlank
    private String title;
    private List<EventShortDto> events;
    @NotNull @NotBlank
    private Boolean pinned;
}
