package io.imob.domain.model.enums;

public enum BuildingType {

    COMERCIAL("Comercial"),
    RESIDENCIAL("Residencial"),
    LOTE("Lote"),
    OUTROS("Outros");

    public final String description;

    BuildingType(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }
}
