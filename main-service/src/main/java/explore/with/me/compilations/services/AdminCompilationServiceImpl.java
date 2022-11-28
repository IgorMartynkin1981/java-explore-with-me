package explore.with.me.compilations.services;

import explore.with.me.compilations.dto.CompilationDto;
import explore.with.me.compilations.dto.CompilationMapper;
import explore.with.me.compilations.dto.NewCompilationDto;
import explore.with.me.compilations.models.Compilation;
import explore.with.me.compilations.repositories.CompilationRepository;
import explore.with.me.events.models.Event;
import explore.with.me.events.services.AdminEventService;
import explore.with.me.events.services.PublicEventService;
import explore.with.me.exeption.ForbiddenException;
import explore.with.me.exeption.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCompilationServiceImpl implements AdminCompilationService {

    private final CompilationRepository compilationRepository;
    private final AdminEventService adminEventService;
    private final PublicEventService publicEventService;

    @Override
    public CompilationDto addNewCompilation(NewCompilationDto newCompilationDto) {
        List<Event> events;
        if (newCompilationDto.getEvents() == null || newCompilationDto.getEvents().isEmpty()) {
            events = new ArrayList<>();
        } else {
            events = adminEventService.getEventsListById(newCompilationDto.getEvents());
        }
        Compilation compilation = compilationRepository.save(
                CompilationMapper.toCompilation(newCompilationDto, events));
        return CompilationMapper.toCompilationDto(compilation);
    }

    @Override
    public void deleteCompilationById(Long compilationId) {
        Compilation compilation = compilationRepository.findById(compilationId).orElseThrow(() -> new NotFoundException(
                "Collection with id = %d was not found in the database" + compilationId));
        compilationRepository.deleteById(compilationId);
    }

    @Override
    public void deleteEventInCompilation(Long compilationId, Long eventId) {
        Compilation compilation = compilationRepository.findById(compilationId).orElseThrow(() -> new NotFoundException(
                "Collection with id = %d was not found in the database" + compilationId));
        deleteEvent(compilation, eventId);
        compilationRepository.save(compilation);
    }

    @Override
    public void addEventInCompilation(Long compilationId, Long eventId) {
        Compilation compilation = compilationRepository.findById(compilationId)
                .orElseThrow(() -> new NotFoundException(
                        "Collection with id = %d was not found in the database" + compilationId));
        for (Event event : compilation.getEvents()) {
            if (event.getId().intValue() == eventId.intValue()) {
                throw new ForbiddenException(String.format("Event with id = %d in collection id = %d already exists",
                        eventId, compilationId));
            }
        }
        compilation.getEvents().add(publicEventService.findEventById(eventId));
        compilationRepository.save(compilation);
    }

    @Override
    public void deletePin(Long compilationId) {
        Compilation compilation = compilationRepository.findById(compilationId)
                .orElseThrow(() -> new NotFoundException(
                        "Collection with id = %d was not found in the database" + compilationId));
        compilation.setPinned(false);
        compilationRepository.save(compilation);
    }

    @Override
    public void toPin(Long compilationId) {
        Compilation compilation = compilationRepository.findById(compilationId)
                .orElseThrow(() -> new NotFoundException(
                        "Collection with id = %d was not found in the database" + compilationId));
        compilation.setPinned(true);
        compilationRepository.save(compilation);
    }

    private Compilation deleteEvent(Compilation compilation, Long eventId) {
        publicEventService.findEventById(eventId);
        int check = 0;
        if (compilation.getEvents() != null) {
            for (int i = compilation.getEvents().size() - 1; i >= 0; i--) {
                if (compilation.getEvents().get(i).getId().intValue() == eventId.intValue()) {
                    compilation.getEvents().remove(i);
                    check++;
                }
            }
        }
        if (check == 0) {
            throw new NotFoundException(String.format("Event with id = %d was not found in subline with id = %d",
                    eventId, compilation.getId()));
        }
        return compilation;
    }
}
