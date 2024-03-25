package io.imob.api.v1.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AddressOutput {

    private UUID id;
    private String cep;
    private String uf;
    private String city;
    private String neighborhood;
    private String street;
    private String number;
    private String complement;
    private LocationOutput location;

}
