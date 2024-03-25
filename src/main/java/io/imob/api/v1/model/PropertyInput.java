package io.imob.api.v1.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
public class PropertyInput {

    @NotBlank
    private String buildingType;

    @NotBlank
    private String propertyType;

    @NotNull
    private Float privateArea;

    @NotNull
    private Float totalArea;

    @NotNull
    private BigDecimal propertyValue;

    @Size(max = 45)
    private String registrationNumber;

    @Size(max = 45)
    private BigDecimal monthlyCondominiumFee;

    @Size(max = 45)
    private BigDecimal annualPropertyTax;

    @Size(max = 45)
    private String registryOffice;

    private Integer numberRooms;

    private Integer numberBathrooms;

    private Integer numberParkingSpaces;

    @Size(max = 2000)
    private String observation;

    @NotNull
    private AddressInput address;

    private Set<UUID> mediaIds;

}
