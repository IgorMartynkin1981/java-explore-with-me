package explore.with.me.comment.dto;

import explore.with.me.comment.models.Comment;
import explore.with.me.events.models.Event;
import explore.with.me.users.models.User;

import java.time.LocalDateTime;

public class CommentMapper {

    public static Comment toComment(NewCommentDto newCommentDto, Event event, User owner) {
        return new Comment(
                LocalDateTime.now(),
                newCommentDto.getComment(),
                newCommentDto.isPositive(),
                event,
                owner
        );
    }

    public static Comment toComment(CommentDto CommentDto, Event event, User owner) {
        return new Comment(
                CommentDto.getCreated(),
                CommentDto.getComment(),
                CommentDto.isPositive(),
                event,
                owner
        );
    }

    public static CommentDto toCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getCreated(),
                comment.getComment(),
                comment.isPositive(),
                comment.getEvent(),
                comment.getOwner()
        );
    }
}
