package explore.with.me.compilations.services;

import explore.with.me.compilations.dto.CompilationDto;
import explore.with.me.compilations.dto.CompilationMapper;
import explore.with.me.compilations.models.Compilation;
import explore.with.me.compilations.repositories.CompilationRepository;
import explore.with.me.exeption.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicCompilationServiceImpl implements PublicCompilationService {
    private final CompilationRepository compilationRepository;

    @Override
    public List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size) {
        PageRequest pageRequest = PageRequest.of(from / size, size, Sort.by("title"));
        Page<Compilation> compilations = compilationRepository.findAllWithPinned(pinned, pageRequest);
        return compilations.stream().map(CompilationMapper::toCompilationDto).collect(Collectors.toList());
    }

    @Override
    public CompilationDto getCompilationById(Long compId) {
        Compilation compilation = compilationRepository.findById(compId).orElseThrow(() -> new NotFoundException(
                String.format("Collection with id = %d was not found in the database", compId)));
        return CompilationMapper.toCompilationDto(compilation);
    }
}
