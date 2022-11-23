package explore.with.me.events.services;

import explore.with.me.client.EventsClient;
import explore.with.me.client.statistic.Hit;
import explore.with.me.client.statistic.Statistic;
import explore.with.me.events.dto.EventFullDto;
import explore.with.me.events.dto.EventMapper;
import explore.with.me.events.dto.EventShortDto;
import explore.with.me.events.models.Event;
import explore.with.me.events.models.State;
import explore.with.me.events.repositories.EventRepository;
import explore.with.me.exeption.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicEventServiceImpl implements PublicEventService {

    private final EventRepository eventRepository;
    private final EventsClient eventsClient;

    @Override
    public Collection<EventShortDto> getAllEvents(String text,
                                                  List<Long> categories,
                                                  Boolean paid,
                                                  String rangeStart,
                                                  String rangeEnd,
                                                  Boolean onlyAvailable,
                                                  String sort,
                                                  Integer from,
                                                  Integer size,
                                                  HttpServletRequest httpServletRequest) {
        addStat(httpServletRequest);
        PageRequest pageRequest = PageRequest.of(from / size, size);
        Collection<Event> events = eventRepository.findEventsByParam(
                text,
                categories,
                paid,
                rangeStart,
                rangeEnd,
                onlyAvailable,
                sort,
                from,
                size,
                pageRequest);
        addViews(events);
        return events.stream().map(EventMapper::toEventShortDto).collect(Collectors.toList());
    }

    @Override
    public EventFullDto getEventById(Long eventId, HttpServletRequest httpServletRequest) {
        addStat(httpServletRequest);
        Event event = findEventById(eventId);
        event = addViews(event, List.of(httpServletRequest.getRequestURI()));
        return EventMapper.toEventFullDto(event);
    }

    @Override
    public Event findEventById(Long eventId) {
        return eventRepository.findById(eventId).filter(event -> event.getState().equals(State.PUBLISHED))
                .orElseThrow(() -> new NotFoundException(
                        String.format("Event with id %d was not found in the database or it has not yet been published",
                                eventId)));
    }

    private void addStat(HttpServletRequest request) {
        Hit hit = new Hit(
                "ewm-main-service",
                request.getRequestURI(),
                request.getRemoteAddr(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        eventsClient.saveStat(hit);
    }

    private Event addViews(Event event, List<String> uris) {
        ResponseEntity<Statistic[]> responseEntity = eventsClient.getStat(uris);
        Statistic[] stat = responseEntity.getBody();
        assert stat != null;
        if (stat.length < 1) {
            event.setViews(0);
            return event;
        }
        event.setViews(stat[0].getHits());
        return event;
    }

    private Collection<Event> addViews(Collection<Event> events) {
        List<String> uris = new ArrayList<>();
        for (Event event : events) {
            uris.add("/events/" + event.getId());
        }
        ResponseEntity<Statistic[]> responseEntity = eventsClient.getStat(uris);
        Statistic[] stat = responseEntity.getBody();
        assert stat != null;
        Map<Long, Integer> views = new HashMap<>();
        for (Statistic statistic : stat) {
            String[] array = statistic.getUri().split("/");
            views.put(Long.valueOf(array[array.length - 1]), statistic.getHits());
        }
        for (Event event : events) {
            if (views.get(event.getId()) == null) {
                event.setViews(0);
            } else {
                event.setViews(views.get(event.getId()));
            }
        }
        return events;
    }
}
