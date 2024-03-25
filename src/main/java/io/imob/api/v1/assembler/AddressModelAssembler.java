package io.imob.api.v1.assembler;

import io.imob.api.v1.model.AddressOutput;
import io.imob.domain.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressModelAssembler {

    private final LocationModelAssembler locationModelAssembler;

    public AddressOutput toModel(Address input) {
        return AddressOutput.builder()
                .id(input.getId())
                .cep(input.getCep())
                .uf(input.getUf().name())
                .city(input.getCity())
                .neighborhood(input.getNeighborhood())
                .street(input.getStreet())
                .number(input.getNumber())
                .complement(input.getComplement())
                .location(locationModelAssembler.toModel(input.getLocation()))
                .build();
    }
}
