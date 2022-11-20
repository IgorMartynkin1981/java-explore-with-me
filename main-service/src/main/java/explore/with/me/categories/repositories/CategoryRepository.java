package explore.with.me.categories.repositories;

import explore.with.me.categories.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
