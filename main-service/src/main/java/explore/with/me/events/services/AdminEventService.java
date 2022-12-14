package explore.with.me.events.services;

import explore.with.me.events.dto.AdminUpdateEventRequest;
import explore.with.me.events.dto.EventFullDto;
import explore.with.me.events.models.Event;

import java.util.Collection;
import java.util.List;

public interface AdminEventService {

    Collection<EventFullDto> getAdminEvents(List<Long> users,
                                            List<String> states,
                                            List<Long> categories,
                                            String rangeStart,
                                            String rangeEnd,
                                            Integer from,
                                            Integer size);

    EventFullDto updateEvent(Long eventId, AdminUpdateEventRequest adminUpdateEventRequest);

    EventFullDto publishEvent(Long eventId);

    EventFullDto rejectEvent(Long eventId);

    Collection<Event> getEventsByCategoryId(Long categoryId);

    List<Event> getEventsListById(List<Long> eventsId);
}
