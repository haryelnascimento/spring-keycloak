package io.imob.infra.storage;

import io.imob.core.storage.StorageProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Config {

    @Bean
    public S3Presigner s3Presigner(StorageProperties storageProperties) {
        StorageProperties.S3 properties = storageProperties.getS3();

        AwsBasicCredentials credentials = AwsBasicCredentials.create(properties.getKeyId(), properties.getKeySecret());
        StaticCredentialsProvider provider = StaticCredentialsProvider.create(credentials);

        return S3Presigner.builder()
                .region(Region.of(properties.getRegion()))
                .credentialsProvider(provider)
                .build();
    }

    @Bean
    public S3Client s3Client(StorageProperties storageProperties) {
        StorageProperties.S3 properties = storageProperties.getS3();

        AwsBasicCredentials credentials = AwsBasicCredentials.create(properties.getKeyId(), properties.getKeySecret());
        StaticCredentialsProvider provider = StaticCredentialsProvider.create(credentials);

        return S3Client.builder()
                .region(Region.of(properties.getRegion()))
                .credentialsProvider(provider)
                .build();
    }
}
