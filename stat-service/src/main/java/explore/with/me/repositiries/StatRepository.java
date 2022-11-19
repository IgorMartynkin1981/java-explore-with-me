package explore.with.me.repositiries;

import explore.with.me.models.Hit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatRepository extends JpaRepository<Hit, Long> {

}
