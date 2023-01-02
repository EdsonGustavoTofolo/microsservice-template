package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Table(name = "cidades")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited(targetAuditMode = NOT_AUDITED)
@Immutable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "cidades")
public class CidadeEntity {
    @Id
    @Column(unique = true)
    private Integer id;
    private String nome;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoEntity estado;
}
