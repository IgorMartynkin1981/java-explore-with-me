package explore.with.me.categories.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * description:	 Категория
 * @id*	Long Идентификатор категории
 * @name* String Название категории
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto extends CategoryMapper {

    @Positive
    @NotNull
    private Long id;
    @NotNull
    @NotBlank
    private String name;

    public CategoryDto(String name) {
        this.name = name;
    }
}
