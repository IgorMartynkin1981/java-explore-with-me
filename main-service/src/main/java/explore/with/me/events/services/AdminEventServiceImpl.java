package explore.with.me.events.services;

import explore.with.me.events.dto.AdminUpdateEventRequest;
import explore.with.me.events.dto.EventFullDto;
import explore.with.me.events.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEventServiceImpl implements AdminEventService {

    private final EventRepository eventRepository;

    @Override
    public Collection<EventFullDto> adminGetEvents(List<Long> users,
                                                   List<String> states,
                                                   List<Long> categories,
                                                   String rangeStart,
                                                   String rangeEnd,
                                                   Integer from,
                                                   Integer size) {
        return null;
    }

    @Override
    public EventFullDto updateEvent(Long eventId, AdminUpdateEventRequest request) {
        return null;
    }

    @Override
    public EventFullDto publishEvent(Long eventId) {
        return null;
    }

    @Override
    public EventFullDto rejectEvent(Long eventId) {
        return null;
    }
}
