package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model;

import io.github.edsongustavotofolo.microservicetemplate.usecases.models.TipoDeContatoEnum;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tipos_de_contatos")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Audited
@AuditOverride(forClass = AuditableEntity.class)
public class TipoDeContatoEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contatos_id", nullable = false, updatable = false)
    private ContatosEntity contatos;
    @Enumerated(EnumType.STRING)
    private TipoDeContatoEnum tipoDeContato;
    private String ddd;
    private String numero;
    private String enderecoEmail;
    private String urlSite;
    private String texto;

    public static TipoDeContatoEntity outro(final String texto) {
        var tipo = new TipoDeContatoEntity();
        tipo.tipoDeContato = TipoDeContatoEnum.OUTRO;
        tipo.texto = texto;
        return tipo;
    }

    public static TipoDeContatoEntity site(final String url) {
        var tipo = new TipoDeContatoEntity();
        tipo.tipoDeContato = TipoDeContatoEnum.SITE;
        tipo.urlSite = url;
        return tipo;
    }

    public static TipoDeContatoEntity email(final String endereco) {
        var tipo = new TipoDeContatoEntity();
        tipo.tipoDeContato = TipoDeContatoEnum.EMAIL;
        tipo.enderecoEmail = endereco;
        return tipo;
    }

    public static TipoDeContatoEntity celular(final String ddd, final String numero) {
        var tipo = new TipoDeContatoEntity();
        tipo.tipoDeContato = TipoDeContatoEnum.CELULAR;
        tipo.ddd = ddd;
        tipo.numero = numero;
        return tipo;
    }

    public static TipoDeContatoEntity telefone(final String ddd, final String numero) {
        var tipo = new TipoDeContatoEntity();
        tipo.tipoDeContato = TipoDeContatoEnum.TELEFONE;
        tipo.ddd = ddd;
        tipo.numero = numero;
        return tipo;
    }
}
