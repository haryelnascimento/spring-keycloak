package io.imob.api.v1.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MediaOutput {

    private UUID id;
    private String type;
    private String filename;
    private String contentType;
    private Long size;
    private boolean publicAccess;
    private String downloadUrl;
}
