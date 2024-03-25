package io.imob.domain.model;

import io.imob.domain.model.enums.MediaType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;


@Getter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "imob_media")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    @Size(max = 255)
    private String filename;

    @Size(max = 100)
    @Column(name = "content_type")
    private String contentType;

    private Long size;

    @Setter
    @Builder.Default
    @Column(name = "is_temporary", nullable = false)
    private boolean temporary = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    protected Media() {
    }

    public String getPath() {
        return this.id + "/" + this.filename;
    }

    public boolean isPublicAccess() {
        return this.type.isPublicAccess();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = (o instanceof HibernateProxy) ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Media media = (Media) o;
        return getId() != null && Objects.equals(getId(), media.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
