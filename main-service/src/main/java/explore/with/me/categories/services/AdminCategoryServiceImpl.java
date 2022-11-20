package explore.with.me.categories.services;

import explore.with.me.categories.dto.CategoryDto;
import explore.with.me.categories.dto.NewCategoryDto;
import explore.with.me.categories.models.Category;
import explore.with.me.categories.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto addNewCategory(NewCategoryDto newCategoryDto) {
        return null;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public void deleteCategory(Long categoryId) {

    }
}
