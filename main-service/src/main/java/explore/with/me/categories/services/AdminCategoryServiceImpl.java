package explore.with.me.categories.services;

import explore.with.me.categories.dto.CategoryDto;
import explore.with.me.categories.dto.CategoryMapper;
import explore.with.me.categories.dto.NewCategoryDto;
import explore.with.me.categories.models.Category;
import explore.with.me.categories.repositories.CategoryRepository;
import explore.with.me.events.services.AdminEventService;
import explore.with.me.exeption.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoryService {

    private final CategoryRepository categoryRepository;
    private final AdminEventService adminEventService;

    @Override
    public CategoryDto addNewCategory(NewCategoryDto newCategoryDto) {
        return CategoryMapper.toCategoryDto(
                categoryRepository.save(CategoryMapper.toCategory(newCategoryDto))
        );
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryDto.getId()).orElseThrow(() -> new NotFoundException(
                String.format("Category with id %d was not found in the database", categoryDto.getId())));
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return CategoryMapper.toCategoryDto(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(
                String.format("Category with id %d was not found in the database", categoryId)));
        if (adminEventService.getEventsByCategoryId(categoryId).size() > 0) {
            categoryRepository.deleteById(categoryId);
        }
    }
}
