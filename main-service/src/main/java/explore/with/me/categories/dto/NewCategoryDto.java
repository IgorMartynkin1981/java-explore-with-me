package explore.with.me.categories.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NewCategoryDto {

    @NotBlank @NotNull
    private String name;
}
