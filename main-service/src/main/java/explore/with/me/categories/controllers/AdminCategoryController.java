package explore.with.me.categories.controllers;

import explore.with.me.categories.dto.CategoryDto;
import explore.with.me.categories.dto.NewCategoryDto;
import explore.with.me.categories.services.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

/**
 * API для работы с категориями для пользователей с ролью админа
 *
 * <p>
 * Создание, изменение и удаление Категорий
 * Обратите внимание: имя категории должно быть уникальным
 * </p>
 */
@RestController
@RequestMapping(path = "/admin/categories")
@Validated
@RequiredArgsConstructor
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    /**
     * Создать новую категорию
     *
     * @param #newCategoryDto принимает данные (name - имя категории, должно быть Уникальным) в формате JSON
     * @return #CategoryDto возвращает данные созданной категории (id & name) в формате JSON
     */
    @PostMapping
    public CategoryDto addNewCategory(@RequestBody @Valid NewCategoryDto newCategoryDto) {
        return adminCategoryService.addNewCategory(newCategoryDto);
    }

    /**
     * Вносит изменения в категорию
     *
     * @param #categoryDto принимает данные (id & name - имя категории, должно быть Уникальным) в формате JSON
     * @return #CategoryDto данные обновлённые данные категории (id & name) в формате JSON
     */
    @PatchMapping
    public CategoryDto updateCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return adminCategoryService.updateCategory(categoryDto);
    }

    /**
     * Удаляет категорию по id
     *
     * <p>
     * Обратите внимание: с категорией не должно быть связано ни одного события
     * </p>
     *
     * @param #categoryId id категории которую требуется удалить
     */
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable @Positive Long categoryId) {
        adminCategoryService.deleteCategory(categoryId);
    }
}
