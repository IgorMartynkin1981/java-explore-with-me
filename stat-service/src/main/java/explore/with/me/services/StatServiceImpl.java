package explore.with.me.services;

import explore.with.me.dto.EndpointHit;
import explore.with.me.dto.ViewStats;
import explore.with.me.repositiries.StatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService{

    private final StatRepository statRepository;

    @Override
    public void hit(EndpointHit endpointHit) {

    }

    @Override
    public Collection<ViewStats> getStat(String start, String end, List<String> uris, Boolean unique) {
        return null;
    }
}
