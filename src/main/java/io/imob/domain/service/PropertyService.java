package io.imob.domain.service;

import io.imob.domain.exception.BusinessException;
import io.imob.domain.filter.PropertyFilter;
import io.imob.domain.model.Media;
import io.imob.domain.model.Property;
import io.imob.domain.model.enums.MediaType;
import io.imob.domain.repository.PropertyRepository;
import io.imob.infra.repository.spec.PropertySpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@AllArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final StorageService storageService;

    public Page<Property> search(PropertyFilter filter, Pageable pageable) {
        return propertyRepository.findAll(PropertySpecs.search(filter), pageable);
    }

    public Property findById(UUID id) {
        return propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Properiedade não encontrada"));
    }

    public Property save(Property property) {
        Objects.requireNonNull(property);
        validate(property);

        for (Media media : property.getMedias()) {
            media.setTemporary(false);
        }

        return propertyRepository.save(property);
    }

    public Property update(UUID id, Property property) {
        Objects.requireNonNull(property);
        validate(property);

        Property propertySave = isExists(id);
        property.getMedias().forEach(file -> propertySave.getMedias().forEach(fileSave -> {
            if (!file.equals(fileSave)) {
                file.setTemporary(false);
                this.storageService.softDelete(fileSave);
            }
        }));

        copyProperties(property, propertySave, "id");

        return propertyRepository.save(property);
    }

    private Property isExists(UUID id) {
        return propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Properiedade não encontrada"));
    }

    public void delete(UUID id) {
        propertyRepository.deleteById(id);
    }

    private void validate(Property property) {
        if (property.getMedias() != null) {
            for (Media media : property.getMedias()) {
                if (!storageService.fileExists(media)) {
                    throw new BusinessException(String.format("Arquivo %s não encontrado", media.getId()));
                }

                if (!MediaType.IMAGE.equals(media.getType()) && !MediaType.VIDEO.equals(media.getType())) {
                    throw new BusinessException(String.format("Arquivo %s deve ser uma imagem ou vídeo", media.getId()));
                }
            }
        }
    }
}
