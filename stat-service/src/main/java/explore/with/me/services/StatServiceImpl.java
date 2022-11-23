package explore.with.me.services;

import explore.with.me.dto.EndpointHit;
import explore.with.me.dto.HitMapper;
import explore.with.me.dto.ViewStats;
import explore.with.me.repositiries.StatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService{

    private final StatRepository statRepository;

    @Override
    public void hit(EndpointHit endpointHit) {
        statRepository.save(HitMapper.toHit(endpointHit));
    }

    @Override
    public Collection<ViewStats> getStat(String start, String end, List<String> uris, Boolean unique) {
        start = URLDecoder.decode(start, StandardCharsets.UTF_8);
        end = URLDecoder.decode(end, StandardCharsets.UTF_8);
        LocalDateTime startTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (unique) {
            return statRepository.getUniqueViews(startTime, endTime, uris);
        } else {
            return statRepository.getNotUniqueViews(startTime, endTime, uris);
        }
    }
}
