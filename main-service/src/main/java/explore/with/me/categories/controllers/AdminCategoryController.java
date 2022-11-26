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
 * Контроллер для пользователей с ролью админа
 * <p>
 * Создание, изменение и удаление Категорий
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
     * @param newCategoryDto принимает данные (name - имя категории) в формате JSON
     * @return возвращает данные созданной категории (id & name) в формате JSON
     */
    @PostMapping
    public CategoryDto addNewCategory(@RequestBody @Valid NewCategoryDto newCategoryDto) {
        return adminCategoryService.addNewCategory(newCategoryDto);
    }

    /**
     * Вносит изменения в категорию
     *
     * @param categoryDto принимает данные (id & name) в формате JSON
     * @return данные обновлённые данные категории (id & name) в формате JSON
     */
    @PatchMapping
    public CategoryDto updateCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return adminCategoryService.updateCategory(categoryDto);
    }

    /**
     * Удаляет категорию по id
     *
     * @param categoryId id категории которую требуется удалить
     */
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable @Positive Long categoryId) {
        adminCategoryService.deleteCategory(categoryId);
    }
}
