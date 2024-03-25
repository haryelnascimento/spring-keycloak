package io.imob.domain.service;

import io.imob.domain.model.Media;

import java.net.URL;

public interface CloudStorageProvider {

    URL generetePresignedUploadUrl(Media media);

    URL generetePresignedDownloadUrl(Media media);

    boolean fileExists(String path);

    void moveFile(String sourcePath, String destinationPath);

    void removeFile(String path);

}
