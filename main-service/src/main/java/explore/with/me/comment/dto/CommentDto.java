package explore.with.me.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import explore.with.me.events.models.Event;
import explore.with.me.users.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * Class используется для работы с комментариями
 *
 *  @created время создания комментария
 *  @comment сам комментарий о Event
 *  @isPositive положительный комментарий (True) или отрицательный (False)
 *  @event само событие
 *  @owner пользователь оставивший комментарий
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    @Positive
    @NotNull
    private Long id;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    @NotNull
    @NotBlank
    private String comment;
    private boolean isPositive;
    @NotNull
    private Event event;
    @NotNull
    private User owner;
}
