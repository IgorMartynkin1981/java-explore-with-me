package explore.with.me.events.services;

import explore.with.me.events.dto.EventFullDto;
import explore.with.me.events.dto.EventShortDto;

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
                                           Integer size);

    EventFullDto getEventById(Long eventId);

}
