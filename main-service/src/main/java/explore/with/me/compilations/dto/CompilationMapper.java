package explore.with.me.compilations.dto;

import explore.with.me.compilations.models.Compilation;
import explore.with.me.events.dto.EventMapper;
import explore.with.me.events.dto.EventShortDto;
import explore.with.me.events.models.Event;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CompilationMapper {

    public static Compilation toCompilation(NewCompilationDto newCompilationDto, List<Event> events) {
        return new Compilation(newCompilationDto.getTitle(), events, newCompilationDto.getPinned());
    }

    public static CompilationDto toCompilationDto(Compilation compilation) {
        List<EventShortDto> eventShortDtos = compilation.getEvents()
                .stream().map(EventMapper::toEventShortDto).collect(Collectors.toList());
        return new CompilationDto(compilation.getId(), compilation.getTitle(), eventShortDtos, compilation.getPinned());
    }
}
