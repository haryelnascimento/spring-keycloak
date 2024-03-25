package io.imob.api.v1.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LocationInput {

    @NotBlank
    private String lat;

    @NotBlank
    private String lng;

}
