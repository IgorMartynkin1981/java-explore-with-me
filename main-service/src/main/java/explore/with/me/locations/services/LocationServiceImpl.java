package explore.with.me.locations.services;

import explore.with.me.exeption.NotFoundException;
import explore.with.me.locations.models.Location;
import explore.with.me.locations.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public Location addLocation(Double lat, Double lon) {
        Location location = new Location(lat, lon);
        return locationRepository.save(location);
    }

    @Override
    public Location getLocationById(Long locationId) {
        return locationRepository.findById(locationId).orElseThrow(() -> new NotFoundException(
                String.format("Category with id %d was not found in the database", locationId)));
    }
}
