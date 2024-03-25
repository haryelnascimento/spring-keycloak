package io.imob.domain.filter;

import io.imob.domain.model.enums.PropertyType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class PropertyFilter {

    private List<PropertyType> propertyTypes;

    private BigDecimal minPropertyValue;
    private BigDecimal maxPropertyValue;

    private BigDecimal minMonthlyCondominiumFee;
    private BigDecimal maxMonthlyCondominiumFee;

    private Integer numberRooms;
    private Integer numberBathrooms;
    private Integer numberParkingSpaces;

    private Float minTotalArea;
    private Float maxTotalArea;

    private Float minPrivateArea;
    private Float maxPrivateArea;

}
