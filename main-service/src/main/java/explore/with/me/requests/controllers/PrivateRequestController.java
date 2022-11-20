package explore.with.me.requests.controllers;

import explore.with.me.requests.dto.ParticipationRequestDto;
import explore.with.me.requests.services.PrivateRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collection;

@RestController
@RequestMapping(path = "/users/{userId}/requests")
@Validated
@RequiredArgsConstructor
public class PrivateRequestController {

    private final PrivateRequestService privateRequestService;

    @GetMapping
    public Collection<ParticipationRequestDto> getRequests(@PathVariable @Positive Long userId) {
        return privateRequestService.getRequests(userId);
    }

    @PostMapping
    public ParticipationRequestDto addRequest(@PathVariable @Positive Long userId,
                                              @RequestParam @Positive Long eventId) {
        return privateRequestService.addRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(@PathVariable @Positive Long userId,
                                                 @PathVariable @Positive Long requestId) {
        return privateRequestService.cancelRequest(userId, requestId);
    }
}
