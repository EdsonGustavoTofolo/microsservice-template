package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "contatos")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Audited
@AuditOverride(forClass = AuditableEntity.class)
public class ContatosEntity extends AuditableEntity implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private FornecedorEntity fornecedor;
    @OneToMany(mappedBy = "contatos", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<TipoDeContatoEntity> tipos;
    private String observacao;
}
