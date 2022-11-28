package explore.with.me.requests.dto;

import explore.with.me.events.models.Event;
import explore.with.me.requests.models.Request;
import explore.with.me.users.models.User;

public class RequestMapper {

    public static Request toRequest(ParticipationRequestDto participationRequestDto, Event event, User requester) {
        return new Request(
                participationRequestDto.getCreated(),
                event,
                requester,
                RequestStatus.valueOf(participationRequestDto.getStatus().toUpperCase())
        );
    }

    public static ParticipationRequestDto toParticipationRequestDto(Request request) {
        return new ParticipationRequestDto(
                request.getId(),
                request.getCreated(),
                request.getEvent().getId(),
                request.getRequester().getId(),
                request.getStatus().toString()
        );
    }
}
