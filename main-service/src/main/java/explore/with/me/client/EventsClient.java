package explore.with.me.client;

import explore.with.me.client.statistic.Hit;
import explore.with.me.client.statistic.Statistic;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Интерфейс ведёт взаимодействие с сервером статистики просмотров Events
 *
 * @saveStat отправляет на сервер статистики информацию о просмотре события
 * @getStat запрашивает у сервера статистики информацию о просмотрах
 */

public interface EventsClient {

    void saveStat(Hit hit);

    ResponseEntity<Statistic[]> getStat(List<String> uris);
}