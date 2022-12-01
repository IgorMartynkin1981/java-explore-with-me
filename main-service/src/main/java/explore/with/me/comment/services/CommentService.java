package explore.with.me.comment.services;

import explore.with.me.comment.dto.CommentDto;
import explore.with.me.comment.dto.NewCommentDto;

import java.util.Collection;

/**
 * Интерфейс для работы с сервисом Комментариев
 *
 * <p> addComment создаёт новый комментарий, для поддержания дискуссии каждый пользователь может оставлять
 * не один комментарий к Event
 * <p> updateComment вносит изменения в комментарий, обратите внимание вносить изменения может только создатель комментария
 * <p> getCommentByCommentId получить комментарий
 * <p> getComments получить все комментарии
 * <p> getCommentsByEventId получить все комментарии к Event
 * <p> getCommentsByOwnerId получить все комментарии определённого пользователя
 * <p> deleteComment удалить комментарий, обратите внимание удалять может или админ или создатель комментария
 */
public interface CommentService {

    CommentDto addComment(NewCommentDto newCommentDto, Long eventId, Long ownerId);

    CommentDto updateComment(CommentDto commentDto, Long ownerId);

    CommentDto getCommentByCommentId(Long commentId);

    Collection<CommentDto> getComments();

    Collection<CommentDto> getCommentsByEventId(Long eventId);

    Collection<CommentDto> getCommentsByOwnerId(Long ownerId);

    void deleteComment(Long commentId, Long ownerId, String role);

}
