package explore.with.me.comment.services;

import explore.with.me.comment.dto.CommentDto;
import explore.with.me.comment.dto.NewCommentDto;
import explore.with.me.comment.models.Comment;
import explore.with.me.comment.repositories.CommentRepository;
import explore.with.me.exeption.BadRequestException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Data
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public CommentDto addComment(NewCommentDto newCommentDto, Long eventId, Long ownerId) {
        if (commentRepository.findCommentByEventAndOwner(eventId, ownerId)) {
            throw new BadRequestException(String.format("The user %d has already created a comment for the Event %d",
                    ownerId, eventId));
        }

        return null;
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto) {
        return null;
    }

    @Override
    public CommentDto getCommentByCommentId(Long commentId) {
        return null;
    }

    @Override
    public Collection<CommentDto> getComments() {
        return null;
    }

    @Override
    public Collection<CommentDto> getCommentsByEventId(Long eventId) {
        return null;
    }

    @Override
    public Collection<CommentDto> getCommentsByOwnerId(Long ownerId) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId, String role) {

    }
}
