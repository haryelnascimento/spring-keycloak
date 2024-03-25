package io.imob.domain.service;

import io.imob.domain.exception.StorageCloudException;
import io.imob.domain.model.Media;
import io.imob.domain.repository.MediaRepository;
import io.imob.domain.service.vo.DownloadRequestResult;
import io.imob.domain.service.vo.UploadRequestResult;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StorageService {

    private static final Logger LOG = LoggerFactory.getLogger(StorageService.class);

    private final CloudStorageProvider cloudStorageProvider;
    private final MediaRepository mediaRepository;

    public UploadRequestResult generatedUploadUrl(Media media) {
        Objects.requireNonNull(media);
        mediaRepository.save(media);
        URL presignedUploadUrl = cloudStorageProvider.generetePresignedUploadUrl(media);

        return new UploadRequestResult(media.getId(), presignedUploadUrl.toString());
    }


    public DownloadRequestResult generateDownloadUrl(Media media) {
        Objects.requireNonNull(media);

        return new DownloadRequestResult(media.getId(), cloudStorageProvider.generetePresignedDownloadUrl(media).toString());
    }

    public boolean fileExists(Media media) {
        Objects.requireNonNull(media);
        return this.cloudStorageProvider.fileExists(media.getPath());
    }

    public void softDelete(Media media) {
        this.cloudStorageProvider.moveFile(media.getPath(), "deleted/" + media.getPath());
    }

    @Scheduled(fixedRate = 1000L)
    @Transactional
    public void removeOldTempFiles() {
        LOG.info("Removing old temporary files");
        OffsetDateTime period = OffsetDateTime.now().minus(Duration.ofDays(1));
        List<Media> fileReferences = this.mediaRepository.findAllByTemporaryIsTrueAndCreatedAtBefore(period);

        for (Media media : fileReferences) {
            this.mediaRepository.delete(media);
            this.mediaRepository.flush();

            try {
                this.cloudStorageProvider.removeFile(media.getPath());
            } catch (StorageCloudException e) {
                LOG.warn(e.getMessage());
            }
        }

    }
}
