package explore.with.me.locations.repositories;

import explore.with.me.locations.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
