package explore.with.me.requests.services;


import explore.with.me.requests.dto.ParticipationRequestDto;

import java.util.Collection;

public interface PrivateRequestService {

    Collection<ParticipationRequestDto> getRequests(Long userId);

    ParticipationRequestDto addRequest(Long userId, Long eventId);

    ParticipationRequestDto cancelRequest(Long userId, Long requestId);
}
