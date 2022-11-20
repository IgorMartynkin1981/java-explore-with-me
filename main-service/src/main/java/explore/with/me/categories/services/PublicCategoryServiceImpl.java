package explore.with.me.categories.services;

import explore.with.me.categories.dto.CategoryDto;
import explore.with.me.categories.models.Category;
import explore.with.me.categories.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicCategoryServiceImpl implements PublicCategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Collection<CategoryDto> getCategories(Integer from, Integer size) {
        return null;
    }

    @Override
    public CategoryDto getCategoryDtoById(Long categoryId) {
        return null;
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return null;
    }
}
