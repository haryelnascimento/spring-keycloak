package io.imob.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Representantive {

    @Column(name = "representative_name")
    private String name;

    @Column(name = "representative_email")
    private String email;

    @Column(name = "representative_telephone")
    private String telephone;

}
