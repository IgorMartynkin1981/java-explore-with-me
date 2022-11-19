package explore.with.me.compilations.repositories;

import explore.with.me.compilations.models.Compilation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {


}
