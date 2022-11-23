package explore.with.me.events.repositories;

import explore.with.me.UtilClass;
import explore.with.me.events.models.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomEventRepositoryImpl implements CustomEventRepository {

    @PersistenceContext
    private final EntityManager em;

    public Collection<Event> findEventsByParam(String text,
                                               List<Long> categories,
                                               Boolean paid,
                                               String rangeStart,
                                               String rangeEnd,
                                               Boolean onlyAvailable,
                                               String sort,
                                               Integer from,
                                               Integer size,
                                               Pageable pageable) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Event.class);
        Root<Event> eventRoot = query.from(Event.class);
        List<Predicate> predicates = new ArrayList<>();
        if (text != null) {
            predicates.add(cb.or(
                            cb.like(cb.lower(eventRoot.get("annotation")), "%" + text.toLowerCase() + "%"),
                            cb.like(cb.lower(eventRoot.get("description")), "%" + text.toLowerCase() + "%")
                    )
            );
        }
        if (categories != null && !categories.isEmpty()) {
            predicates.add(eventRoot.get("category").get("id").in(categories));
        }
        if (paid != null) {
            predicates.add(cb.equal(eventRoot.get("paid"), paid));
        }
        if (rangeStart != null && rangeEnd != null) {
            predicates.add(cb.between(eventRoot.get("eventDate"),
                    UtilClass.toLocalDateTime(rangeStart), UtilClass.toLocalDateTime(rangeEnd)));
        } else {
            predicates.add(cb.greaterThanOrEqualTo(eventRoot.get("eventDate"), LocalDateTime.now()));
        }
        if (onlyAvailable != null) {
            predicates.add(cb.greaterThan(eventRoot.get("participantLimit"), eventRoot.get("confirmedRequests")));
        }
        if (sort != null) {
            if ("EVENT_DATE".equalsIgnoreCase(sort)) {
                query.orderBy(cb.desc(eventRoot.get("eventDate")));
            }
            if ("LIKES".equalsIgnoreCase(sort)) {
                query.orderBy(cb.desc(eventRoot.get("likeCount")));
            }
        }
        query.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(query).getResultList();
    }
}
