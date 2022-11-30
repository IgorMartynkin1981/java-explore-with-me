package explore.with.me.comment.repositories;

import explore.with.me.comment.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    boolean findCommentByEventAndOwner(Long eventId, Long ownerId);
}
