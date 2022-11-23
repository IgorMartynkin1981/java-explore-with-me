package explore.with.me.events.services;

import explore.with.me.events.dto.EventFullDto;
import explore.with.me.events.dto.EventShortDto;
import explore.with.me.events.models.Event;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;


public interface PublicEventService {

    Collection<EventShortDto> getAllEvents(String text,
                                           List<Long> categories,
                                           Boolean paid,
                                           String rangeStart,
                                           String rangeEnd,
                                           Boolean onlyAvailable,
                                           String sort,
                                           Integer from,
                                           Integer size,
                                           HttpServletRequest httpServletRequest);

    EventFullDto getEventById(Long eventId, HttpServletRequest request);

    Event findEventById(Long eventId);
}
