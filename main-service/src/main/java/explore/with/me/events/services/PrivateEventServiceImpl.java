package explore.with.me.events.services;

import explore.with.me.UtilClass;
import explore.with.me.categories.dto.CategoryMapper;
import explore.with.me.categories.models.Category;
import explore.with.me.categories.services.PublicCategoryService;
import explore.with.me.events.dto.*;
import explore.with.me.events.models.Event;
import explore.with.me.events.models.State;
import explore.with.me.events.repositories.EventRepository;
import explore.with.me.exeption.BadRequestException;
import explore.with.me.exeption.ForbiddenException;
import explore.with.me.exeption.NotFoundException;
import explore.with.me.locations.services.LocationService;
import explore.with.me.requests.dto.ParticipationRequestDto;
import explore.with.me.requests.dto.RequestMapper;
import explore.with.me.requests.dto.RequestStatus;
import explore.with.me.requests.models.Request;
import explore.with.me.requests.services.PrivateRequestService;
import explore.with.me.users.models.User;
import explore.with.me.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrivateEventServiceImpl implements PrivateEventService {

    private final EventRepository eventRepository;
    private final LocationService locationService;
    private final UserService userService;
    private final PublicCategoryService categoryService;
    private final PrivateRequestService privateRequestService;

    @Override
    public Collection<EventShortDto> getEventListByUserId(Long userId, Integer from, Integer size) {
        userService.getUserById(userId);
        int page = from / size;
        Collection<Event> eventCollection = eventRepository.findAll(PageRequest.of(page, size))
                .stream()
                .filter(event -> Objects.equals(event.getInitiator().getId(), userId))
                .collect(Collectors.toList());
        return eventCollection.stream().map(EventMapper::toEventShortDto).collect(Collectors.toList());
    }

    @Override
    public EventFullDto getEventById(Long userId, Long eventId) {
        User initiator = userService.getUserById(userId);
        Event event = findEventById(eventId);
        if (!Objects.equals(initiator.getId(), event.getInitiator().getId())) {
            throw new ForbiddenException("Event data can be get only by the user who created it, " +
                    "or by an administrator");
        }
        return EventMapper.toEventFullDto(event);
    }

    @Override
    public EventFullDto addNewEvent(Long userId, NewEventDto newEventDto) {
        checkStartOfEventAtLeast2Hours(newEventDto.getEventDate());
        User initiator = userService.getUserById(userId);
        Category category = CategoryMapper.toCategory(categoryService.getCategoryDtoById(newEventDto.getCategory()));
        newEventDto.setLocation(
                locationService.addLocation(newEventDto.getLocation().getLat(), newEventDto.getLocation().getLon())
        );
        Event event = EventMapper.toEvent(newEventDto, category, initiator);
        event.setInitiator(initiator);
        event.setCategory(category);
        event.setViews(0);
        eventRepository.save(event);
        return EventMapper.toEventFullDto(event);
    }

    @Override
    public EventFullDto updateEvent(Long userId, UpdateEventRequest updateEventRequest) {
        checkStartOfEventAtLeast2Hours(updateEventRequest.getEventDate());
        User initiator = userService.getUserById(userId);
        Event event = findEventById(updateEventRequest.getEventId());
        if (!Objects.equals(initiator.getId(), event.getInitiator().getId())) {
            throw new ForbiddenException("Event data can be changed only by the user who created it, " +
                    "or by an administrator");
        }
        if (event.getState().equals(State.PUBLISHED)) {
            throw new BadRequestException(
                    String.format("Event id=%d is PUBLISHED is not pending moderation", event.getId()));
        }
        event.setState(State.PENDING);
        if (updateEventRequest.getTitle() != null) {
            event.setTitle(updateEventRequest.getTitle());
        }
        if (updateEventRequest.getAnnotation() != null) {
            event.setAnnotation(updateEventRequest.getAnnotation());
        }
        if (updateEventRequest.getCategory() != null) {
            event.setCategory(CategoryMapper.toCategory(
                    categoryService.getCategoryDtoById(updateEventRequest.getCategory())));
        }
        if (updateEventRequest.getDescription() != null) {
            event.setDescription(updateEventRequest.getDescription());
        }
        if (updateEventRequest.getEventDate() != null) {
            event.setEventDate(LocalDateTime.parse(updateEventRequest.getEventDate(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        if (updateEventRequest.getPaid() != null) {
            event.setPaid(updateEventRequest.getPaid());
        }
        if (updateEventRequest.getParticipantLimit() != null) {
            event.setParticipantLimit(updateEventRequest.getParticipantLimit());
        }

        return EventMapper.toEventFullDto(eventRepository.save(event));
    }

    @Override
    public EventFullDto cancelEvent(Long userId, Long eventId) {
        User initiator = userService.getUserById(userId);
        Event event = findEventById(eventId);
        if (!Objects.equals(initiator.getId(), event.getInitiator().getId())) {
            throw new ForbiddenException("Event data can be get and canceled only by the user who created it, " +
                    "or by an administrator");
        }
        if (event.getState().equals(State.PENDING)) {
            event.setState(State.CANCELED);
            eventRepository.save(event);
            return EventMapper.toEventFullDto(event);
        } else {
            throw new BadRequestException(
                    "An Event can only be canceled with the PENDING status. Status of this event - " +
                            event.getState());
        }
    }

    @Override
    public Collection<ParticipationRequestDto> getRequestsByEventId(Long userId, Long eventId) {
        User initiator = userService.getUserById(userId);
        Event event = findEventById(eventId);
        if (!Objects.equals(initiator.getId(), event.getInitiator().getId())) {
            throw new ForbiddenException("Event data can be get and canceled only by the user who created it, " +
                    "or by an administrator");
        }

        return privateRequestService.findAllByEventId(eventId).stream()
                .map(RequestMapper::toParticipationRequestDto).collect(Collectors.toList());

    }

    @Override
    public ParticipationRequestDto confirmOrRejectRequest(Long userId, Long eventId, Long reqId, boolean confirm) {
        Event event = findEventById(eventId);
        if (!event.getRequestModeration()) {
            throw new BadRequestException("Applications for participation in this event do not require " +
                    "confirmation of the initiator of the event. RequestModeration=false");
        }
        if (!Objects.equals(userId, event.getInitiator().getId())) {
            throw new ForbiddenException("Only the initiator of the event can confirm the application for " +
                    "participation. The id of the user confirming the request does not match the id of the " +
                    "event initiator");
        }

        Request request = privateRequestService.getRequest(reqId);
        if (confirm) {
            request.setStatus(RequestStatus.CONFIRMED);
        } else {
            request.setStatus(RequestStatus.REJECTED);
        }
        return RequestMapper.toParticipationRequestDto(privateRequestService.addRequest(request));
    }

    private Event findEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(
                String.format("Event with id %d was not found in the database", eventId)));
    }

    private static void checkStartOfEventAtLeast2Hours(String newEventDate) {
        LocalDateTime eventDate = UtilClass.toLocalDateTime(newEventDate);
        LocalDateTime newTime = LocalDateTime.now().plusHours(2).withNano(0);
        if (eventDate.isBefore(newTime)) {
            throw new ForbiddenException("the date and time of the event cannot be earlier " +
                    "than two hours from the current moment");
        }
    }
}
