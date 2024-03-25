package io.imob.domain.model.enums;

public enum PropertyType {

    APARTAMENTO("Apartamento", BuildingType.RESIDENCIAL),
    CASA("Casa", BuildingType.RESIDENCIAL),
    PREDIO("Prédio", BuildingType.RESIDENCIAL),
    GALPAO("Galpão", BuildingType.COMERCIAL),
    SALA("Sala", BuildingType.COMERCIAL),
    LOTE("Lote", BuildingType.LOTE),
    CHACARA("Chácara", BuildingType.OUTROS),
    SITIO("Sítio", BuildingType.OUTROS),
    FAZENDA("Fazenda", BuildingType.OUTROS);

    public final String description;
    public final String building;

    PropertyType(String desc, BuildingType building) {
        this.description = desc;
        this.building = building.name();
    }

    public String getDescription() {
        return description;
    }

    public String getBuilding() {
        return building;
    }
}
