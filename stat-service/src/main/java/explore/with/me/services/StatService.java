package explore.with.me.services;

import explore.with.me.dto.EndpointHit;
import explore.with.me.dto.ViewStats;

import java.util.Collection;
import java.util.List;

public interface StatService {

    void hit(EndpointHit endpointHit);

    Collection<ViewStats> getStat(String start, String end, List<String> uris, Boolean unique);
}
