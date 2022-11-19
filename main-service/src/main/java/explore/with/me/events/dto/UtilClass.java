package explore.with.me.events.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilClass {

    public static LocalDateTime toLocalDateTime(String value) {
        if (value != null) {
            return LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } else {
            return null;
        }
    }

    public static DateTimeFormatter getFormat() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
}
