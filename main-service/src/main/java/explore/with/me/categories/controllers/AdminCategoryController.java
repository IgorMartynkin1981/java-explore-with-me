package explore.with.me.categories.controllers;

import explore.with.me.categories.dto.CategoryDto;
import explore.with.me.categories.dto.NewCategoryDto;
import explore.with.me.categories.services.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping(path = "/admin/categories")
@Validated
@RequiredArgsConstructor
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    @PostMapping
    public CategoryDto addNewCategory(@RequestBody @Valid NewCategoryDto newCategoryDto) {
        return adminCategoryService.addNewCategory(newCategoryDto);
    }

    @PatchMapping
    public CategoryDto updateCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return adminCategoryService.updateCategory(categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable @Positive Long categoryId) {
        adminCategoryService.deleteCategory(categoryId);
    }
}
