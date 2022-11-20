package explore.with.me.requests.models;

import explore.with.me.events.models.Event;
import explore.with.me.requests.dto.RequestStatus;
import explore.with.me.users.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * description:	 Заявка на участие в событии
 *
 * @id Long Идентификатор заявки
 * @created string Дата и время создания заявки
 * @event {@link explore.with.me.events.models.Event} Идентификатор события
 * @requester {@link User} Идентификатор пользователя, отправившего заявку
 * <p>
 * status	String Статус заявки
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @Column(name = "request_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created", nullable = false)
    private LocalDateTime created;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requester;
    @Column(nullable = false)
    private RequestStatus status;

    public Request(LocalDateTime created, Event event, User requester, RequestStatus status) {
        this.created = created;
        this.event = event;
        this.requester = requester;
        this.status = status;
    }
}
