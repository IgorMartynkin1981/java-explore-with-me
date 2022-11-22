package explore.with.me.events.repositories;

import explore.with.me.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>, CustomEventRepository {

}
