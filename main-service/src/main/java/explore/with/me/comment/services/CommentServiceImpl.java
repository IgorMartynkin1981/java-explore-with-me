package explore.with.me.comment.services;

import explore.with.me.comment.dto.CommentDto;
import explore.with.me.comment.dto.CommentMapper;
import explore.with.me.comment.dto.NewCommentDto;
import explore.with.me.comment.models.Comment;
import explore.with.me.comment.repositories.CommentRepository;
import explore.with.me.events.models.Event;
import explore.with.me.events.services.PublicEventService;
import explore.with.me.exeption.BadRequestException;
import explore.with.me.exeption.NotFoundException;
import explore.with.me.users.models.User;
import explore.with.me.users.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PublicEventService publicEventService;

    @Override
    public CommentDto addComment(NewCommentDto newCommentDto, Long eventId, Long ownerId) {

        Event event = publicEventService.findEventById(eventId);
        User ownerComment = userService.getUserById(ownerId);

        return CommentMapper.toCommentDto(commentRepository.save(
                CommentMapper.toComment(newCommentDto, event, ownerComment)
        ));
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Long ownerId) {
        Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow(
                () -> new NotFoundException(String.format("Comment %d not found", commentDto.getId())));
        if (!Objects.equals(ownerId, comment.getOwner().getId())) {
            throw new BadRequestException("Only the user who created the comment can edit the comment.");
        }
        if (!commentDto.getComment().equals(comment.getComment())) {
            comment.setComment(commentDto.getComment());
        }
        if (commentDto.isPositive() != comment.isPositive()) {
            comment.setPositive(commentDto.isPositive());
        }
        return CommentMapper.toCommentDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto getCommentByCommentId(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(String.format("Comment %d not found", commentId)));
        return CommentMapper.toCommentDto(comment);
    }

    @Override
    public Collection<CommentDto> getComments() {
        return commentRepository.findAll()
                .stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<CommentDto> getCommentsByEventId(Long eventId) {
        return commentRepository.findAllByEventId(eventId)
                .stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<CommentDto> getCommentsByOwnerId(Long ownerId) {
        return commentRepository.findAllByOwnerId(ownerId)
                .stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId, Long ownerId, String role) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(String.format("Comment %d not found", commentId)));
        if (role.equals("Admin") || Objects.equals(comment.getOwner().getId(), ownerId)) {
            commentRepository.deleteById(commentId);
        } else {
            throw new BadRequestException("A comment can be deleted by the user who created the comment or " +
                    "by a user with the \"Admin\" role.");
        }
    }
}
