package explore.with.me.requests.dto;

import explore.with.me.events.dto.UtilClass;
import explore.with.me.events.models.Event;
import explore.with.me.requests.models.Request;
import explore.with.me.users.models.User;

import java.time.LocalDateTime;

public class RequestMapper {

    public static Request toRequest(ParticipationRequestDto participationRequestDto, Event event, User requester) {
        return new Request(
                LocalDateTime.parse(participationRequestDto.getCreated(), UtilClass.getFormat()),
                event,
                requester,
                RequestStatus.valueOf(participationRequestDto.getStatus().toUpperCase())
        );
    }

    public static ParticipationRequestDto toParticipationRequestDto(Request request) {
        return new ParticipationRequestDto(
                request.getId(),
                request.getCreated().format(UtilClass.getFormat()),
                request.getEvent().getId(),
                request.getRequester().getId(),
                request.getStatus().toString()
        );
    }
}
