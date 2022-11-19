package explore.with.me.compilations.services;

import explore.with.me.compilations.dto.CompilationDto;

import java.util.Collection;

public interface PublicCompilationService {

    Collection<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size);

    CompilationDto getCompilationById(Long compId);
}
