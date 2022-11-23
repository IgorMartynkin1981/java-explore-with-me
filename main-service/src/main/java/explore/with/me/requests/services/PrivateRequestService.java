package explore.with.me.requests.services;


import explore.with.me.requests.dto.ParticipationRequestDto;
import explore.with.me.requests.models.Request;

import java.util.Collection;

public interface PrivateRequestService {

    Collection<ParticipationRequestDto> getRequests(Long userId);

    ParticipationRequestDto addRequest(Long userId, Long eventId);

    ParticipationRequestDto cancelRequest(Long userId, Long requestId);

    Request getRequest(Long requestId);

    Request addRequest(Request request);

    Collection<Request> findAllByEventId(Long eventId);
}
