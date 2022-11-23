package explore.with.me.events.services;


import explore.with.me.categories.dto.CategoryMapper;
import explore.with.me.categories.services.PublicCategoryService;
import explore.with.me.events.dto.AdminUpdateEventRequest;
import explore.with.me.events.dto.EventFullDto;
import explore.with.me.events.dto.EventMapper;
import explore.with.me.events.models.Event;
import explore.with.me.events.models.State;
import explore.with.me.events.repositories.EventRepository;
import explore.with.me.exeption.BadRequestException;
import explore.with.me.exeption.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminEventServiceImpl implements AdminEventService {

    private final EventRepository eventRepository;
    private final PublicCategoryService publicCategoryService;

    @Override
    public Collection<EventFullDto> adminGetEvents(List<Long> users,
                                                   List<String> states,
                                                   List<Long> categories,
                                                   String rangeStart,
                                                   String rangeEnd,
                                                   Integer from,
                                                   Integer size) {
        List<State> stateList;
        if (states != null) {
            stateList = states.stream().map(State::valueOf).collect(Collectors.toList());
        } else {
            stateList = null;
        }
        PageRequest pageRequest = PageRequest.of(from / size, size);
        Collection<Event> events = eventRepository.findAll(pageRequest).stream().collect(Collectors.toList());
        if (!users.isEmpty()) {
            events = events.stream().filter(
                    e -> users.contains(e.getInitiator().getId())).collect(Collectors.toSet());
        }
        if (stateList != null) {
            events = events.stream().filter(
                    e -> stateList.contains(e.getState())).collect(Collectors.toSet());
        }
        if (!categories.isEmpty()) {
            events = events.stream().filter(
                    e -> categories.contains(e.getCategory().getId())).collect(Collectors.toSet());
        }

        return events.stream().map(EventMapper::toEventFullDto).collect(Collectors.toList());
    }

    @Override
    public EventFullDto updateEvent(Long eventId, AdminUpdateEventRequest adminUpdateEventRequest) {
        Event event = findEventById(eventId);
        if (adminUpdateEventRequest.getAnnotation() != null) {
            event.setAnnotation(adminUpdateEventRequest.getAnnotation());
        }
        if (adminUpdateEventRequest.getCategory() != null) {
            event.setCategory(CategoryMapper.toCategory(publicCategoryService.getCategoryDtoById(adminUpdateEventRequest.getCategory())));
        }
        if (adminUpdateEventRequest.getDescription() != null) {
            event.setDescription(adminUpdateEventRequest.getDescription());
        }
        if (adminUpdateEventRequest.getEventDate() != null) {
            event.setEventDate(LocalDateTime.parse(adminUpdateEventRequest.getEventDate(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        if (adminUpdateEventRequest.getLocation() != null) {
            event.setLocation(adminUpdateEventRequest.getLocation());
        }
        if (adminUpdateEventRequest.getPaid() != null) {
            event.setPaid(adminUpdateEventRequest.getPaid());
        }
        if (adminUpdateEventRequest.getParticipantLimit() != null) {
            event.setParticipantLimit(adminUpdateEventRequest.getParticipantLimit());
        }
        if (adminUpdateEventRequest.getRequestModeration() != null) {
            event.setRequestModeration(adminUpdateEventRequest.getRequestModeration());
        }
        if (adminUpdateEventRequest.getTitle() != null) {
            event.setTitle(adminUpdateEventRequest.getTitle());
        }

        return EventMapper.toEventFullDto(eventRepository.save(event));
    }

    @Override
    public EventFullDto publishEvent(Long eventId) {
        Event event = findEventById(eventId);
        if (!event.getState().equals(State.PENDING)) {
            throw new BadRequestException(
                    "The event must be in the PENDING state, the status of this event " + event.getState());
        }
        if (event.getEventDate().minusHours(1).isBefore(LocalDateTime.now())) {
            throw new BadRequestException("The event must start no later than one hour after the publication. " +
                    "Start of this event " + event.getEventDate());
        }
        event.setState(State.PUBLISHED);
        event.setPublishedOn(LocalDateTime.now());
        return EventMapper.toEventFullDto(eventRepository.save(event));
    }

    @Override
    public EventFullDto rejectEvent(Long eventId) {
        Event event = findEventById(eventId);
        if (event.getState().equals(State.PUBLISHED)) {
            throw new BadRequestException(
                    "The event must not be in the PUBLISHED status, the status of this event is " + event.getState());
        }
        event.setState(State.CANCELED);
        return EventMapper.toEventFullDto(eventRepository.save(event));
    }

    public Event findEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(
                String.format("Event with id %d was not found in the database", eventId)));
    }

    @Override
    public Collection<Event> getEventsByCategoryId(Long categoryId) {
        return eventRepository.findAllByCategoryId(categoryId);
    }
}
