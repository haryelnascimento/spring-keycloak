package io.imob.domain.model;

import io.imob.domain.model.enums.BuildingType;
import io.imob.domain.model.enums.PropertyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "imob_property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "building_type")
    private BuildingType buildingType;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_type")
    private PropertyType propertyType;

    @Column(name = "number_rooms")
    private Integer numberRooms;

    @Column(name = "number_bathrooms")
    private Integer numberBathrooms;

    @Column(name = "number_parking_spaces")
    private Integer numberParkingSpaces;

    @Column(name = "private_area")
    private Float privateArea;

    @Column(name = "total_area")
    private Float totalArea;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "property_value")
    private BigDecimal propertyValue;

    @Column(name = "monthly_condominium_fee")
    private BigDecimal monthlyCondominiumFee;

    @Column(name = "annual_property_tax")
    private BigDecimal annualPropertyTax;

    @Column(name = "registry_office")
    private String registryOffice;

    @Column(name = "observation")
    private String observation;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, targetEntity = Media.class)
    @JoinTable(name = "imob_property_media", joinColumns = {@JoinColumn(name = "property_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "media_id", nullable = false)})
    private List<Media> medias;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, targetEntity = Location.class)
    @JoinTable(name = "imob_property_area_location", joinColumns = {@JoinColumn(name = "property_id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id")})
    private List<Location> areaLocation;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    protected Property() {
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Property property = (Property) o;
        return getId() != null && Objects.equals(getId(), property.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
