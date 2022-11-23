package explore.with.me.requests.services;

import explore.with.me.requests.dto.ParticipationRequestDto;
import explore.with.me.requests.repositories.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PrivateRequestServiceImpl implements PrivateRequestService {

    private final RequestRepository requestRepository;

    @Override
    public Collection<ParticipationRequestDto> getRequests(Long userId) {
        return null;
    }

    @Override
    public ParticipationRequestDto addRequest(Long userId, Long eventId) {
        return null;
    }

    @Override
    public ParticipationRequestDto cancelRequest(Long userId, Long requestId) {
        return null;
    }
}
