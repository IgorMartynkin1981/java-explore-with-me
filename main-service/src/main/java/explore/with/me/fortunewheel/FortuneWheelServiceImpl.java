package explore.with.me.fortunewheel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class FortuneWheelServiceImpl implements FortuneWheelService {

    @Override
    public String printDayWeek() {
        LocalDateTime d = LocalDateTime.now();
        if (d.getDayOfWeek() == DayOfWeek.THURSDAY) {
            return "Сегодня Четверг";
        }
        String s = String.valueOf(d.getDayOfWeek());
        return s;
    }
}
