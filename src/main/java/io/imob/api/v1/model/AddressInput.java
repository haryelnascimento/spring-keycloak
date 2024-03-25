package io.imob.api.v1.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressInput {

    @NotBlank
    @Size(min = 8, max = 10)
    private String cep;

    @NotBlank
    private String uf;

    @NotBlank
    @Size(min = 2, max = 100)
    private String city;

    @NotBlank
    @Size(min = 2, max = 70)
    private String neighborhood;

    @NotBlank
    @Size(min = 2, max = 70)
    private String street;

    @Size(max = 6)
    private String number;

    @Size(max = 70)
    private String complement;

    private LocationInput location;

}
