package explore.with.me.events.services;

import explore.with.me.events.dto.EventFullDto;
import explore.with.me.events.dto.EventShortDto;
import explore.with.me.events.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicEventServiceImpl implements PublicEventService {

    private final EventRepository eventRepository;

    @Override
    public Collection<EventShortDto> getAllEvents(String text,
                                                  List<Long> categories,
                                                  Boolean paid,
                                                  String rangeStart,
                                                  String rangeEnd,
                                                  Boolean onlyAvailable,
                                                  String sort,
                                                  Integer from,
                                                  Integer size) {
        return null;
    }

    @Override
    public EventFullDto getEventById(Long eventId) {
        return null;
    }
}
