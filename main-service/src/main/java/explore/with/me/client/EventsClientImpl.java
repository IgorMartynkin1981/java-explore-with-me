package explore.with.me.client;

import explore.with.me.UtilClass;
import explore.with.me.client.statistic.Hit;
import explore.with.me.client.statistic.Statistic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventsClientImpl implements EventsClient {

    @Value("${stat-service.url}")
    private String baseUri;

    @Override
    public void saveStat(Hit hit) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Object> requestEntity = new HttpEntity<>(hit, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                baseUri + "/hit",
                HttpMethod.POST,
                requestEntity,
                Object.class
        );
    }

    @Override
    public ResponseEntity<Statistic[]> getStat(List<String> uris) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);
        String start = LocalDateTime.now().format(UtilClass.getFormat());
        String end = LocalDateTime.now().format(UtilClass.getFormat());
        start = URLEncoder.encode(start, StandardCharsets.UTF_8);
        end = URLEncoder.encode(end, StandardCharsets.UTF_8);
        StringBuilder urisBuilder = new StringBuilder();
        for (int i = 0; i < uris.size(); i++) {
            if (i < (uris.size() - 1)) {
                urisBuilder.append("uris").append("=").append(uris.get(i)).append("&");
            } else {
                urisBuilder.append("uris").append("=").append(uris.get(i));
            }
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                baseUri + "/stats?start=" + start + "&end=" + end + "&" + urisBuilder + "&unique=true",
                HttpMethod.GET,
                requestEntity,
                Statistic[].class
        );
    }
}