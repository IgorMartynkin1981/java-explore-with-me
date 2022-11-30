package explore.with.me.comment.services;

import explore.with.me.comment.dto.CommentDto;
import explore.with.me.comment.dto.NewCommentDto;

import java.util.Collection;

/**
 * Интерфейс для работы с сервисом Комментариев
 *
 * addComment создаёт новый комментарий
 * updateComment вносит изменения в комментарий, обратите внимание вносить изменения может только создатель комментария
 * getCommentByCommentId получить комментарий
 * getComments получить все комментарии
 * getCommentsByEventId получить все комментарии к Event
 * getCommentsByOwnerId получить все комментарии определённого пользователя
 * deleteComment удалить комментарий, обратите внимание удалять может или админ или создатель комментария
 */
public interface CommentService {

    CommentDto addComment(NewCommentDto newCommentDto, Long eventId, Long ownerId);

    CommentDto updateComment(CommentDto commentDto);

    CommentDto getCommentByCommentId(Long commentId);

    Collection<CommentDto> getComments();

    Collection<CommentDto> getCommentsByEventId(Long eventId);

    Collection<CommentDto> getCommentsByOwnerId(Long ownerId);

    void deleteComment(Long commentId, String role);

}
