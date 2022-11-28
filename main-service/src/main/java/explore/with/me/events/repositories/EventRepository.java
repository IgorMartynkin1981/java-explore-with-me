package explore.with.me.events.repositories;

import explore.with.me.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface EventRepository extends JpaRepository<Event, Long>, CustomEventRepository {

    Collection<Event> findAllByCategoryId(Long categoryId);
}
