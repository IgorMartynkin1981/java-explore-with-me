package explore.with.me.compilations.services;

import explore.with.me.compilations.dto.CompilationDto;
import explore.with.me.compilations.dto.NewCompilationDto;
import explore.with.me.compilations.repositories.CompilationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCompilationServiceImpl implements AdminCompilationService {
    private final CompilationRepository compilationRepository;

    @Override
    public CompilationDto addNewCompilation(NewCompilationDto newCompilationDto) {
        return null;
    }

    @Override
    public void deleteCompilationById(Long compilationId) {

    }

    @Override
    public void deleteEventInCompilation(Long compilationId, Long eventId) {

    }

    @Override
    public void addEventInCompilation(Long compilationId, Long eventId) {

    }

    @Override
    public void deletePin(Long compilationId) {

    }

    @Override
    public void toPin(Long compilationId) {

    }
}
