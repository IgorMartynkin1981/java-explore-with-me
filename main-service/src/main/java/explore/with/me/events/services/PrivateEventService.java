package explore.with.me.events.services;

import explore.with.me.events.dto.EventFullDto;
import explore.with.me.events.dto.EventShortDto;
import explore.with.me.events.dto.NewEventDto;
import explore.with.me.events.dto.UpdateEventRequest;
import explore.with.me.requests.dto.ParticipationRequestDto;

import java.util.Collection;

public interface PrivateEventService {

    Collection<EventShortDto> getEventListByUserId(Long userId, Integer from, Integer size);

    EventFullDto getEventById(Long userId, Long eventId);

    EventFullDto addNewEvent(Long userId, NewEventDto newEventDto);

    EventFullDto updateEvent(Long userId, UpdateEventRequest updateEventRequest);

    EventFullDto cancelEvent(Long userId, Long eventId);

    Collection<ParticipationRequestDto> getRequestsByEventId(Long userId, Long eventId);

    ParticipationRequestDto confirmOrRejectRequest(Long userId, Long eventId, Long reqId, boolean confirm);

}
