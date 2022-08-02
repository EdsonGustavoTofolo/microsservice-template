package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Table(name = "paises")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Audited(targetAuditMode = NOT_AUDITED)
public class PaisEntity {
    @Id
    @Column(unique = true)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(nullable = false)
    private String nome;
}
