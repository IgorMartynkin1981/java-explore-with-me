package explore.with.me.comment.controllers;

import explore.with.me.comment.dto.CommentDto;
import explore.with.me.comment.dto.NewCommentDto;
import explore.with.me.comment.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Collection;

/**
 * API для работы с комментариями к Events
 *
 * <p> Может ли пользователь изменять комментарий - ДА если его комментарий
 * <p> Кто видит комментарии - ВСЕ
 * <p> Может ли администратор удалить комментарий - да
 */

@RestController
@RequestMapping(path = "/comments")
@Validated
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentDto addComment(@RequestParam @Positive Long eventId,
                                 @RequestParam @Positive Long ownerId,
                                 @RequestBody @Valid NewCommentDto newCommentDto) {
        return commentService.addComment(newCommentDto, eventId, ownerId);
    }

    @PatchMapping("/{ownerId}")
    public CommentDto updateComment(@RequestBody @Valid CommentDto commentDto,
                                    @PathVariable @Positive Long ownerId) {
        return commentService.updateComment(commentDto, ownerId);
    }

    @GetMapping("/{commentId}")
    CommentDto getCommentByCommentId(@PathVariable @Positive Long commentId) {
        return commentService.getCommentByCommentId(commentId);
    }

    @GetMapping
    public Collection<CommentDto> getComments() {
        return commentService.getComments();
    }

    @GetMapping("/events/{eventId}")
    public Collection<CommentDto> getCommentsByEventId(@PathVariable @Positive Long eventId) {
        return commentService.getCommentsByEventId(eventId);
    }

    @GetMapping("/users/{ownerId}")
    public Collection<CommentDto> getCommentsByOwnerId(@PathVariable @Positive Long ownerId) {
        return commentService.getCommentsByOwnerId(ownerId);
    }

    @DeleteMapping("/{commentId}/{ownerId}/{role}")
    public void deleteComment(@PathVariable @Positive Long commentId,
                              @PathVariable @Positive Long ownerId,
                              @PathVariable String role) {
        commentService.deleteComment(commentId, ownerId, role);
    }
}
