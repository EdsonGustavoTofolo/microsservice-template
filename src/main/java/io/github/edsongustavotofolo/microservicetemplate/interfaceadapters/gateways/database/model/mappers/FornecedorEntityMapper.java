package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.mappers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Celular;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contatos;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Email;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Endereco;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.OutroContato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Site;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Telefone;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.CidadeImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.ContatosImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.EnderecoImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.EstadoImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.FornecedorImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.PaisImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cep;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.ContatosEntity;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.EnderecoEntity;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.FornecedorEntity;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FornecedorEntityMapper {

    FornecedorEntityMapper INSTANCE = Mappers.getMapper(FornecedorEntityMapper.class);

    default Cnpj toCnpj(final String cnpj) {
        return StringUtils.isEmpty(cnpj) ? null : new Cnpj(cnpj);
    }

    default Endereco toEndereco(final EnderecoEntity enderecoEntity) {
        if (enderecoEntity == null) {
            return null;
        }
        return new EnderecoImpl(
                enderecoEntity.getId(),
                enderecoEntity.getLogradouro(),
                enderecoEntity.getNumero(),
                enderecoEntity.getBairro(),
                enderecoEntity.getComplemento(),
                enderecoEntity.getPontoDeReferencia(),
                new Cep(enderecoEntity.getCep()),
                new CidadeImpl(enderecoEntity.getCidade().getId(),
                        enderecoEntity.getCidade().getNome(),
                        new EstadoImpl(
                                enderecoEntity.getCidade().getEstado().getId(),
                                enderecoEntity.getCidade().getEstado().getNome(),
                                enderecoEntity.getCidade().getEstado().getSigla(),
                                new PaisImpl(
                                        enderecoEntity.getCidade().getEstado().getPais().getId(),
                                        enderecoEntity.getCidade().getEstado().getPais().getNome()
                                )
                        ))
        );
    }

    default Contatos toContatos(final ContatosEntity contatosEntity) {
        if (contatosEntity == null) {
            return null;
        }
        final var contatos = new ContatosImpl(contatosEntity.getId(), contatosEntity.getObservacao());

        contatosEntity.getTipos().forEach(tipoDeContatoEntity -> {
            switch (tipoDeContatoEntity.getTipoDeContato()) {
                case EMAIL -> {
                    contatos.add(
                            new Email(tipoDeContatoEntity.getId(), tipoDeContatoEntity.getEnderecoEmail()));
                }
                case TELEFONE -> {
                    contatos.add(
                            new Telefone(tipoDeContatoEntity.getId(), tipoDeContatoEntity.getDdd(), tipoDeContatoEntity.getNumero()));
                }
                case CELULAR -> {
                    contatos.add(
                            new Celular(tipoDeContatoEntity.getId(), tipoDeContatoEntity.getDdd(), tipoDeContatoEntity.getNumero()));
                }
                case SITE -> {
                    contatos.add(
                            new Site(tipoDeContatoEntity.getId(), tipoDeContatoEntity.getUrlSite()));
                }
                case OUTRO -> {
                    contatos.add(
                            new OutroContato(tipoDeContatoEntity.getId(), tipoDeContatoEntity.getTexto()));
                }
            }
        });

        return contatos;
    }

    @Named("contatosToContatosEntity")
    static ContatosEntity contatosListaToTipoDeContatoEntity(final Contatos contatos) {
        final var contatosEntity = new ContatosEntity();

        final var tipoDeContatoEntities = contatos.getLista().stream().map(contato -> {
            final var tipoDeContatoEntity = TipoDeContatoEntityMapper.INSTANCE.map(contato);
            tipoDeContatoEntity.setContatos(contatosEntity);
            return tipoDeContatoEntity;
        }).toList();

        contatosEntity.setTipos(tipoDeContatoEntities);
        contatosEntity.setObservacao(contatos.getObservacao());

        return contatosEntity;
    }

    @Mapping(target = "cnpj", expression = "java(fornecedor.getCnpj().toString())")
    @Mapping(target = "endereco.cep", expression = "java(endereco.getCep().toString())")
    @Mapping(target = "contatos", source = "contatos", qualifiedByName = "contatosToContatosEntity")
    FornecedorEntity toEntity(final Fornecedor fornecedor);

    FornecedorImpl toDomain(final FornecedorEntity entity);
}
