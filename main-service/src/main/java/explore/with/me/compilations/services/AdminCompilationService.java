package explore.with.me.compilations.services;

import explore.with.me.compilations.dto.CompilationDto;
import explore.with.me.compilations.dto.NewCompilationDto;

public interface AdminCompilationService {

    CompilationDto addNewCompilation(NewCompilationDto newCompilationDto);

    void deleteCompilationById(Long compilationId);

    void deleteEventInCompilation(Long compilationId, Long eventId);

    void addEventInCompilation(Long compilationId, Long eventId);

    void deletePin(Long compilationId);

    void toPin(Long compilationId);
}
