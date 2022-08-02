package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Table(name = "cidades")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited(targetAuditMode = NOT_AUDITED)
public class CidadeEntity {
    @Id
    @Column(unique = true)
    private Integer id;
    private String nome;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoEntity estado;
}
