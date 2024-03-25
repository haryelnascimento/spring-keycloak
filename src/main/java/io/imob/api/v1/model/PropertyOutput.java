package io.imob.api.v1.model;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class PropertyOutput {

    private UUID id;
    private String buildingType;
    private String propertyType;
    private Integer numberRooms;
    private Integer numberBathrooms;
    private Integer numberParkingSpaces;
    private Float privateArea;
    private Float totalArea;
    private String registrationNumber;
    private BigDecimal propertyValue;
    private BigDecimal monthlyCondominiumFee;
    private BigDecimal annualPropertyTax;
    private String registryOffice;
    private String observation;
    private AddressOutput address;
    List<MediaOutput> medias;
}
