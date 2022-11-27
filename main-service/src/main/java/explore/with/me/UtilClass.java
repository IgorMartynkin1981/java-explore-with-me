package explore.with.me;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс работает по переводу даты из строки в дату и обратно
 *
 * @author Igor Martynkin
 * @version v 0.9
 */
public class UtilClass {

    /**
     * Преобразует строковое значение в LocalDateTime
     * @param value строковое значение даты в формате "yyyy-MM-dd HH:mm:ss"
     * @return значение даты в формате LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String value) {
        if (value != null) {
            return LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } else {
            return null;
        }
    }

    /**
     * Применять для форматирования даты в формате "yyyy-MM-dd HH:mm:ss"
     * @return DateTimeFormatter
     */
    public static DateTimeFormatter getFormat() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
}
