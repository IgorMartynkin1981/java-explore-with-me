package explore.with.me.events.dto;

import explore.with.me.locations.models.Location;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class NewEventDto {

    @NotNull @Length(max = 1000)
    private String annotation;
    @NotNull @Positive
    private Long category;
    @NotNull @Length(max = 2000)
    private String description;
    @NotNull @NotBlank
    private String eventDate;
    @NotNull
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    @NotNull @Length(max = 255)
    private String title;
}
