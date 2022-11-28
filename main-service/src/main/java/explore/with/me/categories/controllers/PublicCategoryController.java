package explore.with.me.categories.controllers;

import explore.with.me.categories.dto.CategoryDto;
import explore.with.me.categories.services.PublicCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;

/**
 * Публичный API для работы с категориями
 *
 * <p> Контроллер для запросов от не авторизованных пользователей
 * <p> Может выводить все категории или определённую категорию
 */
@RestController
@RequestMapping(path = "/categories")
@Validated
@RequiredArgsConstructor
public class PublicCategoryController {

    private final PublicCategoryService publicCategoryService;

    /**
     * Выводит все категории
     *
     * <p>
     * может указать параметры для вывода на экран
     * параметры устанавливаются в строке запроса
     * </p>
     *
     * @param #from количество категорий, которые нужно пропустить для формирования текущего набора
     * @param #size количество категорий в наборе
     * @return данные данные категории (id & name) в формате JSON
     */
    @GetMapping
    public Collection<CategoryDto> getCategories(@RequestParam(name = "from", defaultValue = "0")
                                                 @PositiveOrZero Integer from,
                                                 @RequestParam(name = "size", defaultValue = "10")
                                                 @Positive Integer size) {
        return publicCategoryService.getCategories(from, size);
    }

    /**
     * Выводит категорию по заданному id
     *
     * @param catId id категории
     * @return данные запрошенной категории (id & name) в формате JSON
     */
    @GetMapping("/{catId}")
    public CategoryDto getCategoryById(@PathVariable @Positive Long catId) {
        return publicCategoryService.getCategoryDtoById(catId);
    }
}
