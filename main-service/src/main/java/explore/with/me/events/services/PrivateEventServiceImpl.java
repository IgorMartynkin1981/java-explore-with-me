package explore.with.me.events.services;

import explore.with.me.events.dto.EventFullDto;
import explore.with.me.events.dto.EventShortDto;
import explore.with.me.events.dto.NewEventDto;
import explore.with.me.events.dto.UpdateEventRequest;
import explore.with.me.events.repositories.EventRepository;
import explore.with.me.locations.repositories.LocationRepository;
import explore.with.me.requests.dto.ParticipationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PrivateEventServiceImpl implements PrivateEventService{

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    @Override
    public Collection<EventShortDto> getEventListByUserId(Long userId, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventFullDto updateEvent(Long userId, UpdateEventRequest updateEventRequest) {
        return null;
    }

    @Override
    public EventFullDto addNewEvent(Long userId, NewEventDto newEventDto) {
        return null;
    }

    @Override
    public EventFullDto getEventById(Long userId, Long eventId) {
        return null;
    }

    @Override
    public EventFullDto cancelEvent(Long userId, Long eventId) {
        return null;
    }

    @Override
    public Collection<ParticipationRequestDto> getRequestsByEventId(Long userId, Long eventId) {
        return null;
    }

    @Override
    public ParticipationRequestDto confirmOrRejectRequest(Long userId, Long eventId, Long reqId, boolean confirm) {
        return null;
    }
}
