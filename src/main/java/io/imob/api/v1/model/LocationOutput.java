package io.imob.api.v1.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LocationOutput {

    private UUID id;
    private String lat;
    private String lng;

}
