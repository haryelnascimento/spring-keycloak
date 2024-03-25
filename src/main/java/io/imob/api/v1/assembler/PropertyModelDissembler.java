package io.imob.api.v1.assembler;

import io.imob.api.v1.model.PropertyInput;
import io.imob.domain.model.Media;
import io.imob.domain.model.Property;
import io.imob.domain.model.enums.BuildingType;
import io.imob.domain.model.enums.PropertyType;
import io.imob.domain.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PropertyModelDissembler {

    private final MediaRepository mediaRepository;
    private final AddressModelDissembler addressModelDissembler;

    public Property toDomain(PropertyInput input) {
        return Property.builder()
                .buildingType(BuildingType.valueOf(input.getBuildingType()))
                .propertyType(PropertyType.valueOf(input.getPropertyType()))
                .privateArea(input.getPrivateArea())
                .totalArea(input.getTotalArea())
                .propertyValue(input.getPropertyValue())
                .registrationNumber(input.getRegistrationNumber())
                .monthlyCondominiumFee(input.getMonthlyCondominiumFee())
                .annualPropertyTax(input.getAnnualPropertyTax())
                .registryOffice(input.getRegistryOffice())
                .numberRooms(input.getNumberRooms())
                .numberBathrooms(input.getNumberBathrooms())
                .numberParkingSpaces(input.getNumberParkingSpaces())
                .observation(input.getObservation())
                .address(addressModelDissembler.toDomain(input.getAddress()))
                .medias(findAllMediasByIds(input.getMediaIds()))
                .build();
    }

    public Property toDomain(UUID id, PropertyInput input) {
        return Property.builder()
                .id(id)
                .buildingType(BuildingType.valueOf(input.getBuildingType()))
                .propertyType(PropertyType.valueOf(input.getPropertyType()))
                .privateArea(input.getPrivateArea())
                .totalArea(input.getTotalArea())
                .propertyValue(input.getPropertyValue())
                .registrationNumber(input.getRegistrationNumber())
                .monthlyCondominiumFee(input.getMonthlyCondominiumFee())
                .annualPropertyTax(input.getAnnualPropertyTax())
                .registryOffice(input.getRegistryOffice())
                .numberRooms(input.getNumberRooms())
                .numberBathrooms(input.getNumberBathrooms())
                .numberParkingSpaces(input.getNumberParkingSpaces())
                .observation(input.getObservation())
                .address(addressModelDissembler.toDomain(input.getAddress()))
                .medias(findAllMediasByIds(input.getMediaIds()))
                .build();
    }

    private List<Media> findAllMediasByIds(Set<UUID> ids) {
        return mediaRepository.findAllById(ids);
    }
}
