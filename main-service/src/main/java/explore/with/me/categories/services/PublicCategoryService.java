package explore.with.me.categories.services;

import explore.with.me.categories.dto.CategoryDto;

import java.util.Collection;

/**
 * Класс инструкций для поиска и получения Категории пользователями с любыми ролями
 */

public interface PublicCategoryService {

    Collection<CategoryDto> getCategories(Integer from, Integer size);

    CategoryDto getCategoryDtoById(Long categoryId);
}
