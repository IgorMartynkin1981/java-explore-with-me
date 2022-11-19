package explore.with.me.requests.repositories;

import explore.with.me.requests.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {

}
