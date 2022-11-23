package explore.with.me.repositiries;

import explore.with.me.dto.ViewStats;
import explore.with.me.models.Hit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface StatRepository extends JpaRepository<Hit, Long> {

    @Query("select new explore.with.me.dto.ViewStats(h.app, h.uri, count(distinct h.ip)) "
            + " from Hit h "
            + " where h.timestamp between ?1 and ?2 "
            + " and h.uri in (?3) "
            + " group by h.app, h.uri")
    Collection<ViewStats> getUniqueViews(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("select new explore.with.me.dto.ViewStats(h.app, h.uri, count(h.ip)) "
            + " from Hit h "
            + " where h.timestamp between ?1 and ?2 "
            + " and h.uri in (?3) "
            + " group by h.app, h.uri")
    Collection<ViewStats> getNotUniqueViews(LocalDateTime start, LocalDateTime end, List<String> uris);

}
