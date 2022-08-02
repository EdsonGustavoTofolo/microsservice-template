package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Table(name = "estados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited(targetAuditMode = NOT_AUDITED)
public class EstadoEntity {
    @Id
    @Column(unique = true)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sigla;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pais_id", nullable = false)
    private PaisEntity pais;
}
