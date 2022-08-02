package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Audited
@AuditOverride(forClass = AuditableEntity.class)
public class EnderecoEntity extends AuditableEntity implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private FornecedorEntity fornecedor;
    @Column(nullable = false)
    private String logradouro;
    @Column(nullable = false, length = 10)
    private String numero;
    @Column(nullable = false, length = 60)
    private String bairro;
    @Column(length = 100)
    private String complemento;
    @Column(length = 100)
    private String pontoDeReferencia;
    @Column(nullable = false, length = 8)
    private String cep;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id", nullable = false)
    private CidadeEntity cidade;
}
