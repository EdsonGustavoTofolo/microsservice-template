package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fornecedores")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Audited
@AuditOverride(forClass = AuditableEntity.class)
public class FornecedorEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(nullable = false, length = 14, unique = true)
    private String cnpj;
    @Column(nullable = false)
    private String razaoSocial;
    @Column(nullable = false)
    private String nomeFantasia;
    @OneToOne(mappedBy = "fornecedor", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private EnderecoEntity endereco;
    private String observacao;
    @OneToOne(mappedBy = "fornecedor", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ContatosEntity contatos;
}
