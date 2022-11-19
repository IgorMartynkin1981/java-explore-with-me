package explore.with.me.events.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class UpdateEventRequest {

    private String annotation;
    private Long category;
    private String description;
    private String eventDate;
    @NotNull @Positive
    private Long eventId;
    private Boolean paid;
    private Integer participantLimit;
    private String title;
}
