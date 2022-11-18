package explore.with.me.categories.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CategoryDto {

    @Positive @NotNull
    private Long id;
    @NotNull @NotBlank
    private String name;

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
