package io.imob.domain.repository;

import io.imob.domain.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface MediaRepository extends JpaRepository<Media, UUID> {

    List<Media> findAllByTemporaryIsTrueAndCreatedAtBefore(OffsetDateTime now);

}
