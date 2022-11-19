package explore.with.me.events.dto;


import explore.with.me.categories.dto.CategoryMapper;
import explore.with.me.categories.models.Category;
import explore.with.me.events.models.Event;
import explore.with.me.events.models.State;
import explore.with.me.users.dto.UserMapper;
import explore.with.me.users.models.User;

import java.time.LocalDateTime;

public class EventMapper {

    public static Event toEvent(NewEventDto newEventDto, Category category, User initiator) {
        LocalDateTime eventDate = LocalDateTime.parse(newEventDto.getEventDate(), UtilClass.getFormat());
        return new Event(newEventDto.getTitle(),
                newEventDto.getAnnotation(),
                category,
                0,
                LocalDateTime.now(),
                newEventDto.getDescription(),
                eventDate,
                initiator,
                newEventDto.getLocation(),
                newEventDto.getPaid(),
                newEventDto.getParticipantLimit(),
                null,
                newEventDto.getRequestModeration(),
                State.PENDING,
                0);
    }

    public static EventFullDto toEventFullDto(Event event) {
        EventFullDto eventFullDto = new EventFullDto(
                event.getId(),
                event.getTitle(),
                event.getAnnotation(),
                CategoryMapper.toCategoryDto(event.getCategory()),
                event.getConfirmedRequests(),
                event.getCreatedOn().format(UtilClass.getFormat()),
                event.getDescription(),
                event.getEventDate().format(UtilClass.getFormat()),
                UserMapper.toUserShortDto(event.getInitiator()),
                event.getLocation(),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getRequestModeration(),
                event.getState(),
                event.getViews());
        if (event.getPublishedOn() != null) {
            eventFullDto.setPublishedOn(event.getPublishedOn().format(UtilClass.getFormat()));
        }
        return eventFullDto;
    }

    public static EventShortDto toEventShortDto(Event event) {
        return new EventShortDto(
                event.getId(),
                event.getTitle(),
                event.getAnnotation(),
                CategoryMapper.toCategoryDto(event.getCategory()),
                event.getConfirmedRequests(),
                event.getEventDate().format(UtilClass.getFormat()),
                UserMapper.toUserShortDto(event.getInitiator()),
                event.getPaid(),
                event.getViews());
    }
}
