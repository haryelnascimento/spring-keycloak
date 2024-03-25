package io.imob.domain.model;

import io.imob.domain.model.enums.TemplateStyle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "imob_partner")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "active")
    private Boolean active = true;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "name")
    private String name;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "link")
    private String link;

    @Column(name = "template_style")
    @Enumerated(EnumType.STRING)
    private TemplateStyle templateStyle;

    @Column(name = "summary")
    private String summary;

    @Embedded
    private Representantive representantive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "logo_id")
    private Media logo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "favicon_id")
    private Media favicon;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    protected Partner() {
    }


}
