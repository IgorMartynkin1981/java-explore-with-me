package explore.with.me.compilations.models;

import explore.with.me.events.models.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Подборка событий
 * @id*	[...]
 * @title*	String Заголовок подборки
 * @events	{@link Event} Список событий входящих в подборку
 * @pinned*	boolean Закреплена ли подборка на главной странице сайта
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "compilations")
public class Compilation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compilation_id")
    private Long id;
    @Column(nullable = false)
    private String title;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "event_compilations",
            joinColumns = {@JoinColumn(name = "compilation_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    private List<Event> events;
    @Column(nullable = false)
    private Boolean pinned;

    public Compilation(String title, List<Event> events, Boolean pinned) {
        this.title = title;
        this.events = events;
        this.pinned = pinned;
    }
}
