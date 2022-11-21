package explore.with.me.events.services;

import explore.with.me.categories.dto.CategoryMapper;
import explore.with.me.categories.models.Category;
import explore.with.me.categories.services.PublicCategoryService;
import explore.with.me.events.dto.*;
import explore.with.me.events.models.Event;
import explore.with.me.events.repositories.EventRepository;
import explore.with.me.locations.services.LocationService;
import explore.with.me.requests.dto.ParticipationRequestDto;
import explore.with.me.users.models.User;
import explore.with.me.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PrivateEventServiceImpl implements PrivateEventService {

    private final EventRepository eventRepository;
    private final LocationService locationService;
    private final UserService userService;
    private final PublicCategoryService categoryService;

    @Override
    public Collection<EventShortDto> getEventListByUserId(Long userId, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventFullDto getEventById(Long userId, Long eventId) {
        return null;
    }

    @Override
    public EventFullDto addNewEvent(Long userId, NewEventDto newEventDto) {
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
