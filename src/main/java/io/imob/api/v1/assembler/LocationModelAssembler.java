package io.imob.api.v1.assembler;

import io.imob.api.v1.model.LocationOutput;
import io.imob.domain.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationModelAssembler {

    public LocationOutput toModel(Location location) {
        return LocationOutput.builder()
                .id(location.getId())
                .lat(location.getLat())
                .lng(location.getLng())
                .build();
    }

}
