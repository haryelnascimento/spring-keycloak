package io.imob.domain.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UploadRequestResult {

    private UUID id;
    private String url;

}
