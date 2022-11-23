package explore.with.me.events.controllers;

import explore.with.me.events.dto.EventFullDto;
import explore.with.me.events.dto.EventShortDto;
import explore.with.me.events.services.PublicEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
public class PublicEventController {

    private final PublicEventService publicEventService;

    @GetMapping
    public Collection<EventShortDto> getAllEvents(@RequestParam(required = false) String text,
                                                  @RequestParam(required = false) List<Long> categories,
                                                  @RequestParam(required = false) Boolean paid,
                                                  @RequestParam(required = false) String rangeStart,
                                                  @RequestParam(required = false) String rangeEnd,
                                                  @RequestParam(required = false) Boolean onlyAvailable,
                                                  @RequestParam(required = false) String sort,
                                                  @RequestParam(name = "from", defaultValue = "0")
                                                  @PositiveOrZero Integer from,
                                                  @RequestParam(name = "size", defaultValue = "10")
                                                  @Positive Integer size,
                                                  HttpServletRequest httpServletRequest) {
        return publicEventService.getAllEvents(
                text,
                categories,
                paid,
                rangeStart,
                rangeEnd,
                onlyAvailable,
                sort,
                from,
                size,
                httpServletRequest);
    }

    @GetMapping("/{id}")
    public EventFullDto getEventById(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        return publicEventService.getEventById(id, httpServletRequest);
    }
}
