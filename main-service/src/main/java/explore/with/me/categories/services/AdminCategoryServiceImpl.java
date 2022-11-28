package explore.with.me.categories.services;

import explore.with.me.categories.dto.CategoryDto;
import explore.with.me.categories.dto.CategoryMapper;
import explore.with.me.categories.dto.NewCategoryDto;
import explore.with.me.categories.models.Category;
import explore.with.me.categories.repositories.CategoryRepository;
import explore.with.me.events.models.Event;
import explore.with.me.events.services.AdminEventService;
import explore.with.me.exeption.ForbiddenException;
import explore.with.me.exeption.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
        Collection<Event> events = adminEventService.getEventsByCategoryId(categoryId);
        if (events.size() > 0) {
            throw new ForbiddenException(String.format("A category that has events cannot be deleted. " +
                    "There are events in the category with id = %d with the following id: %s", categoryId, events));
        }
        categoryRepository.deleteById(categoryId);
    }
}
