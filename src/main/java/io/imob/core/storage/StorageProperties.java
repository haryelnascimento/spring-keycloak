package io.imob.core.storage;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.net.URL;

@Data
@Validated
@Configuration
@ConfigurationProperties("imob.storage")
public class StorageProperties {

    @Valid
    private final S3 s3 = new S3();
    private final Image image = new Image();

    @Data
    public static class S3 {

        @NotBlank
        private String keyId;

        @NotBlank
        private String keySecret;

        @NotBlank
        private String bucket;

        @NotBlank
        private String region;
    }

    @Data
    public class Image {

        @NotNull
        private URL downloadUrl;
    }

    @Data
    public class Document {

        @NotNull
        private URL downloadUrl;
    }

}