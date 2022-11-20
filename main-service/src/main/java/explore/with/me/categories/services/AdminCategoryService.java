package explore.with.me.categories.services;

import explore.with.me.categories.dto.CategoryDto;
import explore.with.me.categories.dto.NewCategoryDto;
import explore.with.me.categories.models.Category;

/**
 * Класс инструкций для создания и изменения Категории пользователями с ролью Админ
 */

public interface AdminCategoryService {

    CategoryDto addNewCategory(NewCategoryDto newCategoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto);

    void deleteCategory(Long categoryId);


}
