package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Endereco;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.CidadeImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.EnderecoImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.FornecedorImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cep;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ContatoMapper.class})
public interface FornecedorMapper {

    FornecedorMapper INSTANCE = Mappers.getMapper(FornecedorMapper.class);

    default Fornecedor map(final CreateFornecedor createFornecedor) {
        return toDomain(createFornecedor);
    }

    @Named("mapEndereco")
    default Endereco mapEndereco(final CreateFornecedor createFornecedor) {
        return new EnderecoImpl(
                createFornecedor.getLogradouro(),
                createFornecedor.getNumero(),
                createFornecedor.getBairro(),
                createFornecedor.getComplemento(),
                createFornecedor.getPontoDeReferencia(),
                new Cep(createFornecedor.getCep()),
                new CidadeImpl(createFornecedor.getCidade())
        );
    }

    Cnpj map(final String numero);

    @Mapping(target = "endereco", source = ".", qualifiedByName = "mapEndereco")
    FornecedorImpl toDomain(final CreateFornecedor createFornecedor);
}
