package explore.with.me.events.controllers;

import explore.with.me.events.dto.EventFullDto;
import explore.with.me.events.dto.EventShortDto;
import explore.with.me.events.dto.NewEventDto;
import explore.with.me.events.dto.UpdateEventRequest;
import explore.with.me.events.services.PrivateEventService;
import explore.with.me.requests.dto.ParticipationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;

@RestController
@RequestMapping(path = "/users/{userId}/events")
@Validated
@RequiredArgsConstructor
public class PrivateEventController {

    private final PrivateEventService privateEventService;

    @GetMapping
    public Collection<EventShortDto> getEventListByUserId(@PathVariable @Positive Long userId,
                                                          @RequestParam(name = "from", defaultValue = "0") @PositiveOrZero
                                                          Integer from,
                                                          @RequestParam(name = "size", defaultValue = "10") @Positive
                                                          Integer size) {
        return privateEventService.getEventListByUserId(userId, from, size);
    }

    @PatchMapping
    public EventFullDto updateEvent(@PathVariable @Positive Long userId,
                                    @RequestBody @Valid UpdateEventRequest updateEventRequest) {
        return privateEventService.updateEvent(userId, updateEventRequest);
    }

    @PostMapping
    public EventFullDto addNewEvent(@PathVariable @Positive Long userId,
                                    @RequestBody @Valid NewEventDto newEventDto) {
        return privateEventService.addNewEvent(userId, newEventDto);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventById(@PathVariable @Positive Long userId,
                                     @PathVariable @Positive Long eventId) {
        return privateEventService.getEventById(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto cancelEvent(@PathVariable @Positive Long userId,
                                    @PathVariable @Positive Long eventId) {
        return privateEventService.cancelEvent(userId, eventId);
    }

    @GetMapping("/{eventId}/requests")
    public Collection<ParticipationRequestDto> getRequestsByEventId(@PathVariable @Positive Long userId,
                                                                    @PathVariable @Positive Long eventId) {
        return privateEventService.getRequestsByEventId(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public ParticipationRequestDto confirmRequest(@PathVariable @Positive Long userId,
                                                  @PathVariable @Positive Long eventId,
                                                  @PathVariable @Positive Long reqId) {
        return privateEventService.confirmOrRejectRequest(userId, eventId, reqId, true);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public ParticipationRequestDto rejectRequest(@PathVariable @Positive Long userId,
                                                 @PathVariable @Positive Long eventId,
                                                 @PathVariable @Positive Long reqId) {
        return privateEventService.confirmOrRejectRequest(userId, eventId, reqId, false);
    }
}
