package explore.with.me.compilations.controllers;


import explore.with.me.compilations.dto.CompilationDto;
import explore.with.me.compilations.services.PublicCompilationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;


@RestController
@RequestMapping(path = "/compilations")
@Validated
@RequiredArgsConstructor
public class PublicCompilationController {

    private final PublicCompilationService publicCompilationService;

    @GetMapping
    public Collection<CompilationDto> getCompilations(@RequestParam(required = false) Boolean pinned,
                                                      @RequestParam(name = "from", defaultValue = "0") @PositiveOrZero Integer from,
                                                      @RequestParam(name = "size", defaultValue = "10") @Positive Integer size) {
        return publicCompilationService.getCompilations(pinned, from, size);
    }

    @GetMapping("/{compilationId}")
    public CompilationDto getCompilationById(@PathVariable @Positive Long compilationId) {
        return publicCompilationService.getCompilationById(compilationId);
    }
}
