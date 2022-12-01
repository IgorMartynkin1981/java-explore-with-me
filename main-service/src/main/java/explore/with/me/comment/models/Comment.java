package explore.with.me.comment.models;

import explore.with.me.events.models.Event;
import explore.with.me.users.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class comment служит для создания комментариев пользователей о Event
 *
 * @created время создания комментария
 * @comment сам комментарий о Event
 * @isPositive положительный комментарий (True) или отрицательный (False)
 * @event само событие
 * @owner пользователь оставивший комментарий
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "comment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created", nullable = false)
    private LocalDateTime created;
    @Column(name = "comment", nullable = false)
    private String comment;
    @Column(name = "is_positive")
    private boolean isPositive;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Comment(LocalDateTime created, String comment, boolean isPositive, Event event, User owner) {
        this.created = created;
        this.comment = comment;
        this.isPositive = isPositive;
        this.event = event;
        this.owner = owner;
    }
}
