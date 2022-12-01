package explore.with.me.comment.repositories;

import explore.with.me.comment.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Query("SELECT c FROM Comment AS c WHERE c.event.id = ?1")
    List<Comment> findAllByEventId(Long eventId);

    @Modifying
    @Query("SELECT c FROM Comment AS c WHERE c.owner.id = ?1")
    List<Comment> findAllByOwnerId(Long ownerId);
}

