package explore.with.me.fortunewheel;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/fortune_wheel")
public class FortuneWheelController {
    private final FortuneWheelService fortuneWheelService;

    @GetMapping
    public String printDayWeek() {
        return fortuneWheelService.printDayWeek();
    }
}
