package explore.with.me.compilations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * description:	 Подборка событий
 *
 * @events List [ 1, 2, 3 ] Список идентификаторов событий входящих в подборку
 * @pinned Boolean {default: false} Закреплена ли подборка на главной странице сайта
 * @title* String Заголовок подборки
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCompilationDto {
    List<Long> events;
    private Boolean pinned;
    @NotNull
    @NotBlank
    private String title;
}
