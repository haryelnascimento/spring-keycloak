package io.imob.infra.storage;

import io.imob.core.storage.StorageProperties;
import io.imob.domain.exception.StorageCloudException;
import io.imob.domain.model.Media;
import io.imob.domain.service.CloudStorageProvider;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.awscore.AwsRequestOverrideConfiguration;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URL;
import java.time.Duration;

@Component
@AllArgsConstructor
public class S3CloudStorageProvider implements CloudStorageProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(S3CloudStorageProvider.class);
    private static final Duration DURATION = Duration.ofMinutes(30);

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final StorageProperties storageProperties;

    @Override
    public URL generetePresignedUploadUrl(Media media) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(getBucket())
                .key(media.getPath())
                .contentType(media.getContentType())
                .contentLength(media.getSize())
                .acl(media.isPublicAccess() ? "public-read" : null)
                .overrideConfiguration(overrideConfigBuilder(media).build())
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(DURATION)
                .putObjectRequest(objectRequest)
                .build();

        return s3Presigner.presignPutObject(presignRequest).url();
    }

    @Override
    public URL generetePresignedDownloadUrl(Media media) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(getBucket())
                .key(media.getPath())
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(DURATION)
                .getObjectRequest(getObjectRequest)
                .build();

        return s3Presigner.presignGetObject(presignRequest).url();
    }

    @Override
    public boolean fileExists(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return false;
        }

        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(getBucket())
                .key(filePath)
                .build();

        try {
            s3Client.getObject(request);
            return true;
        } catch (NoSuchKeyException e) {
            LOGGER.warn(String.format("Arquivo não encontrado na nuvem %s", filePath));
            return false;
        }
    }

    @Override
    public void moveFile(String fromPath, String toPath) {
        CopyObjectRequest copyObjectRequest = CopyObjectRequest.builder()
                .sourceKey(fromPath)
                .destinationKey(toPath)
                .sourceBucket(getBucket())
                .destinationBucket(getBucket())
                .build();

        try {
            s3Client.copyObject(copyObjectRequest);
        } catch (S3Exception e) {
            LOGGER.error(String.format("Erro ao copiar o arquivo %s para %s", fromPath, toPath), e);
            throw new StorageCloudException(String.format("Erro ao copiar o arquivo %s para %s", fromPath, toPath));
        }

        removeFile(fromPath);
    }

    @Override
    public void removeFile(String filePath) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(getBucket())
                .key(filePath)
                .build();

        try {
            s3Client.deleteObject(deleteObjectRequest);
        } catch (S3Exception e) {
            LOGGER.error(String.format("Erro ao remover arquivo %s", filePath), e);
            throw new StorageCloudException(String.format("Erro ao remover arquivo %s", filePath));
        }
    }

    private String getBucket() {
        return storageProperties.getS3().getBucket();
    }

    private static AwsRequestOverrideConfiguration.Builder overrideConfigBuilder(Media media) {
        var builderConfig = AwsRequestOverrideConfiguration.builder();

        if (media.isPublicAccess()) {
            builderConfig.putRawQueryParameter("x-amz-acl", "public-read");
        }
        return builderConfig;
    }
}
