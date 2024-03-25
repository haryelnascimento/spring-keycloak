package io.imob.api.v1.assembler;

import io.imob.api.v1.model.LocationInput;
import io.imob.domain.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LocationModelDissembler {

    public Location toDomain(LocationInput input) {
        if (Objects.isNull(input)) {
            return null;
        }

        return Location.builder()
                .lat(input.getLat())
                .lng(input.getLng())
                .build();
    }

    public Location toDomain(UUID id, LocationInput input) {
        return Location.builder()
                .id(id)
                .lat(input.getLat())
                .lng(input.getLng())
                .build();
    }

}
