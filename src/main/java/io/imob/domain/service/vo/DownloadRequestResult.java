package io.imob.domain.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DownloadRequestResult {

    private UUID id;

    private String signedUrl;

}
