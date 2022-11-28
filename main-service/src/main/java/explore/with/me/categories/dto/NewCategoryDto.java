package explore.with.me.categories.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * description: Данные для добавления новой категории
 *
 * @name* String Название категории
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCategoryDto extends CategoryMapper {

    @NotBlank
    @NotNull
    private String name;
}
