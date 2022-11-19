package explore.with.me.compilations.controllers;

import explore.with.me.compilations.dto.CompilationDto;
import explore.with.me.compilations.dto.NewCompilationDto;
import explore.with.me.compilations.services.AdminCompilationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping(path = "/admin/compilations")
@Validated
@RequiredArgsConstructor
public class AdminCompilationsController {

    private final AdminCompilationService compilationService;

    @PostMapping
    public CompilationDto addNewCompilation(@RequestBody @Valid NewCompilationDto newCompilationDto) {
        return compilationService.addNewCompilation(newCompilationDto);
    }

    @DeleteMapping("/{compilationId}")
    public void deleteCompilationById(@PathVariable @Positive Long compilationId) {
        compilationService.deleteCompilationById(compilationId);
    }

    @DeleteMapping("/{compilationId}/events/{eventId}")
    public void deleteEventInCompilation(@PathVariable @Positive Long compilationId,
                                         @PathVariable @Positive Long eventId) {
        compilationService.deleteEventInCompilation(compilationId, eventId);
    }

    @PatchMapping("/{compilationId}/events/{eventId}")
    public void addEventInCompilation(@PathVariable @Positive Long compilationId,
                                      @PathVariable @Positive Long eventId) {
        compilationService.addEventInCompilation(compilationId, eventId);
    }

    @DeleteMapping("/{compilationId}/pin")
    public void deletePin(@PathVariable @Positive Long compilationId) {
        compilationService.deletePin(compilationId);
    }

    @PatchMapping("/{compilationId}/pin")
    public void toPin(@PathVariable @Positive Long compilationId) {
        compilationService.toPin(compilationId);
    }

}
