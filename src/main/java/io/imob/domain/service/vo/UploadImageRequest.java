package io.imob.domain.service.vo;

import io.imob.domain.model.Media;
import io.imob.domain.model.enums.MediaType;
import io.imob.core.validation.AllowedContentTypes;
import io.imob.core.validation.AllowedFileExtensions;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UploadImageRequest {

    @NotBlank
    @AllowedFileExtensions({"jpg", "jpeg", "png"})
    private String fileName;

    @NotBlank
    @AllowedContentTypes({"image/jpeg", "image/png"})
    private String contentType;

    @NotNull
    @Min(1)
    private Long size;

    public Media toDomain() {
        return Media.builder()
                .type(MediaType.IMAGE)
                .filename(this.fileName)
                .contentType(this.contentType)
                .size(this.size)
                .build();
    }

}
