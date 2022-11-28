package explore.with.me.compilations.repositories;

import explore.with.me.compilations.models.Compilation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompilationRepository extends JpaRepository<Compilation, Long>, JpaSpecificationExecutor<Compilation> {

    default Page<Compilation> findAllWithPinned(Boolean pinned, PageRequest pageable) {
        return new PageImpl<>(findAll(specWithPinned(pinned, pageable)));
    }

    default Specification<Compilation> specWithPinned(Boolean pinned, PageRequest pageable) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("pinned"), pinned));
    }

}
