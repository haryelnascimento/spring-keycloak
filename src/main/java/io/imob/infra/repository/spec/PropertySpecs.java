package io.imob.infra.repository.spec;

import io.imob.domain.filter.PropertyFilter;
import io.imob.domain.model.Property;
import io.imob.domain.model.Property_;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class PropertySpecs {

    public static Specification<Property> search(PropertyFilter filter) {
        return (root, query, builder) -> {

            var predicates = new ArrayList<Predicate>();

            if (filter.getPropertyTypes() != null && !filter.getPropertyTypes().isEmpty()) {
                predicates.add(root.get(Property_.PROPERTY_TYPE).in(filter.getPropertyTypes()));
            }

            if (filter.getMinPropertyValue() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get(Property_.PROPERTY_VALUE), filter.getMinPropertyValue()));
            }

            if (filter.getMaxPropertyValue() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get(Property_.PROPERTY_VALUE), filter.getMaxPropertyValue()));
            }

            if (filter.getMinMonthlyCondominiumFee() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get(Property_.MONTHLY_CONDOMINIUM_FEE), filter.getMinMonthlyCondominiumFee()));
            }

            if (filter.getMaxMonthlyCondominiumFee() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get(Property_.MONTHLY_CONDOMINIUM_FEE), filter.getMaxMonthlyCondominiumFee()));
            }

            if (filter.getNumberRooms() != null && filter.getNumberRooms() > 0) {
                predicates.add(builder.equal(root.get(Property_.NUMBER_ROOMS), filter.getNumberRooms()));
            }

            if (filter.getNumberBathrooms() != null && filter.getNumberBathrooms() > 0) {
                predicates.add(builder.equal(root.get(Property_.NUMBER_BATHROOMS), filter.getNumberBathrooms()));
            }

            if (filter.getNumberParkingSpaces() != null && filter.getNumberParkingSpaces() > 0) {
                predicates.add(builder.equal(root.get(Property_.NUMBER_PARKING_SPACES), filter.getNumberParkingSpaces()));
            }

            if (filter.getMinTotalArea() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get(Property_.TOTAL_AREA), filter.getMinTotalArea()));
            }

            if (filter.getMaxTotalArea() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get(Property_.TOTAL_AREA), filter.getMaxTotalArea()));
            }

            if (filter.getMinPrivateArea() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get(Property_.PRIVATE_AREA), filter.getMinPrivateArea()));
            }

            if (filter.getMaxPrivateArea() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get(Property_.PRIVATE_AREA), filter.getMaxPrivateArea()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
