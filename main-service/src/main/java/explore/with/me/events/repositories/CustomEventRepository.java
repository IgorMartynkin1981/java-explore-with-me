package explore.with.me.events.repositories;

import explore.with.me.events.models.Event;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface CustomEventRepository {

    Collection<Event> findEventsByParam(String text,
                                        List<Long> categories,
                                        Boolean paid,
                                        String rangeStart,
                                        String rangeEnd,
                                        Boolean onlyAvailable,
                                        String sort,
                                        Integer from,
                                        Integer size,
                                        Pageable pageable);

}
