package explore.with.me.controllers;

import explore.with.me.services.StatService;
import explore.with.me.dto.EndpointHit;
import explore.with.me.dto.ViewStats;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class Controller {

    private final StatService statService;

    @PostMapping("/hit")
    public void hit(@RequestBody EndpointHit endpointHit) {
        statService.hit(endpointHit);
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getStat(@RequestParam @NotBlank String start,
                                          @RequestParam @NotBlank String end,
                                          @RequestParam @NotNull List<String> uris,
                                          @RequestParam Boolean unique) {
        Collection<ViewStats> views = statService.getStat(start, end, uris, unique);
        return new ResponseEntity<>(views, HttpStatus.OK);
    }

}
