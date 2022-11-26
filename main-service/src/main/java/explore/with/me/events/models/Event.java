package explore.with.me.events.models;

import explore.with.me.categories.models.Category;
import explore.with.me.locations.models.Location;
import explore.with.me.users.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @id Long Идентификатор
 * @title* String Заголовок
 * @annotation* String Краткое описание
 * @category* CategoryDto {@link Category}  Категория
 * @confirmedRequests Количество одобренных заявок на участие в данном событии
 * @createdOn String Дата и время создания события (в формате "yyyy-MM-dd HH:mm:ss")
 * @description String Полное описание события
 * @eventDate* String Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
 * @initiator* UserShortDto {@link User} Пользователь (краткая информация)
 * @location* Location {@link Location} Широта и долгота места проведения события
 * @paid* boolean Нужно ли оплачивать участие
 * @participantLimit Integer Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
 * @publishedOn String Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss")
 * @requestModeration boolean {default: true} Нужна ли пре-модерация заявок на участие
 * @state String {@link State} Список состояний жизненного цикла события
 * @views integer Количество просмотрев события
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(name = "event_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String annotation;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "confirmed_requests", nullable = false)
    private Integer confirmedRequests;
    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;
    @Column(nullable = false)
    private String description;
    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User initiator;
    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @Column(nullable = false)
    private Boolean paid;
    @Column(name = "participant_limit", nullable = false)
    private Integer participantLimit;
    @Column(name = "published_on")
    private LocalDateTime publishedOn;
    @Column(name = "request_moderation", nullable = false)
    private Boolean requestModeration;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;
    @Transient
    private Integer views;

    public Event(String title, String annotation, Category category, Integer confirmedRequests,
                 LocalDateTime createdOn, String description, LocalDateTime eventDate, User initiator,
                 Location location, Boolean paid, Integer participantLimit, LocalDateTime publishedOn,
                 Boolean requestModeration, State state, Integer views) {
        this.title = title;
        this.annotation = annotation;
        this.category = category;
        this.confirmedRequests = confirmedRequests;
        this.createdOn = createdOn;
        this.description = description;
        this.eventDate = eventDate;
        this.initiator = initiator;
        this.location = location;
        this.paid = paid;
        this.participantLimit = participantLimit;
        this.publishedOn = publishedOn;
        this.requestModeration = requestModeration;
        this.state = state;
        this.views = views;
    }
}
