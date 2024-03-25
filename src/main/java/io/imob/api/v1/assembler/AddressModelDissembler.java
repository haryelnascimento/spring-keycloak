package io.imob.api.v1.assembler;

import io.imob.api.v1.model.AddressInput;
import io.imob.domain.model.Address;
import io.imob.domain.model.enums.UF;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AddressModelDissembler {

    private final LocationModelDissembler locationModelDissembler;

    public Address toDomain(AddressInput input) {
        Objects.requireNonNull(input);

        return Address.builder()
                .cep(input.getCep())
                .uf(UF.valueOf(input.getUf()))
                .city(input.getCity())
                .neighborhood(input.getNeighborhood())
                .street(input.getStreet())
                .number(input.getNumber())
                .complement(input.getComplement())
                .location(locationModelDissembler.toDomain(input.getLocation()))
                .build();
    }

    public Address toDomain(UUID id, AddressInput input) {
        return Address.builder()
                .id(id)
                .cep(input.getCep())
                .uf(UF.valueOf(input.getUf()))
                .city(input.getCity())
                .neighborhood(input.getNeighborhood())
                .street(input.getStreet())
                .number(input.getNumber())
                .complement(input.getComplement())
                .location(locationModelDissembler.toDomain(input.getLocation()))
                .build();
    }

}
