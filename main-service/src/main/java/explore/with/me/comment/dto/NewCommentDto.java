package explore.with.me.comment.dto;

import explore.with.me.events.models.Event;
import explore.with.me.users.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Class используется для создания новых комментариев
 *
 *  @comment сам комментарий о Event
 *  @isPositive положительный комментарий (True) или отрицательный (False)
 *  @event само событие
 *  @owner пользователь оставивший комментарий
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCommentDto {
    @NotNull
    @NotBlank
    private String comment;
    private boolean isPositive;
}
