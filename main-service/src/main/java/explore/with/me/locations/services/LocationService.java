package explore.with.me.locations.services;

import explore.with.me.locations.models.Location;

public interface LocationService {

    Location addLocation(Double lat, Double lon);

    Location getLocationById(Long locationId);
}
