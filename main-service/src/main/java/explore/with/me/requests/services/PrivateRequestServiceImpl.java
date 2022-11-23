package explore.with.me.requests.services;

import explore.with.me.events.models.Event;
import explore.with.me.events.models.State;
import explore.with.me.events.services.PublicEventService;
import explore.with.me.exeption.BadRequestException;
import explore.with.me.exeption.NotFoundException;
import explore.with.me.requests.dto.ParticipationRequestDto;
import explore.with.me.requests.dto.RequestMapper;
import explore.with.me.requests.dto.RequestStatus;
import explore.with.me.requests.models.Request;
import explore.with.me.requests.repositories.RequestRepository;
import explore.with.me.users.models.User;
import explore.with.me.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrivateRequestServiceImpl implements PrivateRequestService {

    private final RequestRepository requestRepository;
    private final UserService userService;
    private final PublicEventService publicEventService;

    @Override
    public Collection<ParticipationRequestDto> getRequests(Long userId) {
        userService.getUserById(userId);
        List<Request> requestList = requestRepository.findAllByRequesterId(userId);
        return requestList.stream().map(RequestMapper::toParticipationRequestDto).collect(Collectors.toList());
    }

    @Override
    public ParticipationRequestDto addRequest(Long userId, Long eventId) {
        User requester = userService.getUserById(userId);
        Event event = checkRequest(userId, eventId);
        Request request = new Request(LocalDateTime.now(), event, requester);
        if (event.getRequestModeration()) {
            request.setStatus(RequestStatus.PENDING);
        } else {
            request.setStatus(RequestStatus.CONFIRMED);
        }
        return RequestMapper.toParticipationRequestDto(requestRepository.save(request));
    }

    @Override
    public ParticipationRequestDto cancelRequest(Long userId, Long requestId) {
        userService.getUserById(userId);
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Participation request with id = %d was " +
                        "not found in the database"));
        request.setStatus(RequestStatus.CANCELED);
        return RequestMapper.toParticipationRequestDto(requestRepository.save(request));
    }

    @Override
    public Request getRequest(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Participation request with id = %d was " +
                        "not found in the database"));
    }

    @Override
    public Request addRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public Collection<Request> findAllByEventId(Long eventId) {
        return requestRepository.findAllByEventId(eventId);
    }

    private Event checkRequest(Long userId, Long eventId) {
        if (!requestRepository.findByEventAndRequester(userId, eventId).isEmpty()) {
            throw new BadRequestException(String.format(
                    "Event id = %d from user id = %d has already been requested.", eventId, userId));
        }
        Event event = publicEventService.findEventById(eventId);
        if (event.getInitiator().getId().equals(userId)) {
            throw new BadRequestException(String.format(
                    "The user cannot apply to participate in his event. Applicant user id - %d, " +
                            "event initiator id - %d", userId, event.getInitiator().getId()));
        }
        if (!event.getState().equals(State.PUBLISHED)) {
            throw new BadRequestException(
                    "You cannot apply for an unpublished event. Event status " + event.getState());
        }
        if (event.getConfirmedRequests() >= event.getParticipantLimit()) {
            throw new BadRequestException(String.format(
                    "The limit of requests for event id = %d has been exhausted. ConfirmedRequests = %d, " +
                            "ParticipantLimit = %d",
                    eventId, event.getConfirmedRequests(), event.getParticipantLimit()));
        }
        return event;
    }
}
