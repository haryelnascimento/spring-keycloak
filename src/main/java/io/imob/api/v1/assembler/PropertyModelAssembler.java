package io.imob.api.v1.assembler;

import io.imob.api.v1.model.MediaOutput;
import io.imob.api.v1.model.PropertyOutput;
import io.imob.core.storage.StorageProperties;
import io.imob.domain.model.Media;
import io.imob.domain.model.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PropertyModelAssembler {

    private final AddressModelAssembler addressModelAssembler;
    private final StorageProperties storageProperties;

    public PropertyOutput toModel(Property property) {

        PropertyOutput.PropertyOutputBuilder builder = PropertyOutput.builder()
                .id(property.getId())
                .buildingType(property.getBuildingType().name())
                .propertyType(property.getPropertyType().name())
                .numberRooms(property.getNumberRooms())
                .numberBathrooms(property.getNumberBathrooms())
                .numberParkingSpaces(property.getNumberParkingSpaces())
                .privateArea(property.getPrivateArea())
                .totalArea(property.getTotalArea())
                .registrationNumber(property.getRegistrationNumber())
                .propertyValue(property.getPropertyValue())
                .monthlyCondominiumFee(property.getMonthlyCondominiumFee())
                .annualPropertyTax(property.getAnnualPropertyTax())
                .registryOffice(property.getRegistryOffice())
                .observation(property.getObservation())
                .address(addressModelAssembler.toModel(property.getAddress()));

        if (property.getMedias() != null && !property.getMedias().isEmpty()) {
            builder.medias(property.getMedias().stream().map(this::createImage).collect(Collectors.toList()));
        }

        return builder.build();
    }

    private MediaOutput createImage(Media media) {
        String downloadUrl = storageProperties.getImage().getDownloadUrl().toString() + "/" + media.getPath();

        return MediaOutput.builder()
                .id(media.getId())
                .type(media.getType().name())
                .filename(media.getFilename())
                .contentType(media.getContentType())
                .size(media.getSize())
                .publicAccess(media.isPublicAccess())
                .downloadUrl(downloadUrl)
                .build();
    }

}
