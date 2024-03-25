package io.imob.api.v1.controller;

import io.imob.domain.model.Media;
import io.imob.domain.repository.MediaRepository;
import io.imob.domain.service.StorageService;
import io.imob.domain.service.vo.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/medias")
public class MediaController {

    private final StorageService storageService;
    private final MediaRepository mediaRepository;

    @GetMapping("downloads/{id}/{fileName}")
    public ResponseEntity<DownloadRequestResult> downloadRequest(@PathVariable UUID id, @PathVariable String fileName) {
        Media media = mediaRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (media.isPublicAccess()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, storageService.generateDownloadUrl(media).getSignedUrl());

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PostMapping("/images")
    public UploadRequestResult uploadImageRequest(@RequestBody @Valid UploadImageRequest request) {
        return storageService.generatedUploadUrl(request.toDomain());
    }

    @PostMapping("/videos")
    public UploadRequestResult uploadImageRequest(@RequestBody @Valid UploadVideoRequest request) {
        return storageService.generatedUploadUrl(request.toDomain());
    }

    @PostMapping("/documents")
    public UploadRequestResult uploadImageRequest(@RequestBody @Valid UploadDocumentRequest request) {
        return storageService.generatedUploadUrl(request.toDomain());
    }
}
