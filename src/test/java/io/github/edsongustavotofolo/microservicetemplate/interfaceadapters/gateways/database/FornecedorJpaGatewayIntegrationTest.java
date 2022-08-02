package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database;

import io.github.edsongustavotofolo.microservicetemplate.infrastructure.configuration.database.repository.AuditConfiguration;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.*;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.mappers.FornecedorEntityMapper;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository.CidadeJpaRepository;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository.FornecedorJpaRepository;
import io.github.edsongustavotofolo.microservicetemplate.usecases.gateways.FornecedorDsGateway;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static io.github.edsongustavotofolo.microservicetemplate.domain.builder.FornecedorBuilder.umFornecedor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@DataJpaTest
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@EntityScan
@ContextConfiguration(classes = AuditConfiguration.class)
@TestPropertySource("classpath:application.properties")
class FornecedorJpaGatewayIntegrationTest {

    @SpyBean
    private FornecedorJpaRepository fornecedorJpaRepository;
    @Autowired
    private CidadeJpaRepository cidadeJpaRepository;

    private final FornecedorEntityMapper fornecedorEntityMapper = Mappers.getMapper(FornecedorEntityMapper.class);

    private FornecedorDsGateway fornecedorJpaGateway;

    @BeforeEach
    public void setup() {
        this.fornecedorJpaGateway = new FornecedorJpaGateway(this.fornecedorJpaRepository, this.cidadeJpaRepository, this.fornecedorEntityMapper);
    }

    @Test
    void deveCriarFornecedorComSucesso() {
        // cenario
        var fornecedor = umFornecedor().get();

        // execucao
        var id = this.fornecedorJpaGateway.criar(fornecedor);

        // verificacao
        assertNotNull(id);

        ArgumentCaptor<FornecedorEntity> argumentCaptor = ArgumentCaptor.forClass(FornecedorEntity.class);

        verify(fornecedorJpaRepository).persist(argumentCaptor.capture());

        var fornecedorEntity = argumentCaptor.getValue();
        assertNotNull(fornecedorEntity);
        assertNotNull(fornecedorEntity.getId());
        assertEquals(id, fornecedorEntity.getId());
        assertNotNull(fornecedorEntity.getCreatedAt());
        assertNotNull(fornecedorEntity.getCreatedBy());
        assertNotNull(fornecedorEntity.getLastModifiedAt());
        assertNotNull(fornecedorEntity.getLastModifiedBy());

        assertNotNull(fornecedorEntity.getEndereco());
        assertNotNull(fornecedorEntity.getEndereco().getId());
        assertNotNull(fornecedorEntity.getEndereco().getCreatedAt());
        assertNotNull(fornecedorEntity.getEndereco().getCreatedBy());
        assertNotNull(fornecedorEntity.getEndereco().getLastModifiedAt());
        assertNotNull(fornecedorEntity.getEndereco().getLastModifiedBy());

        assertNotNull(fornecedorEntity.getContatos());
        assertNotNull(fornecedorEntity.getContatos().getId());
        assertNotNull(fornecedorEntity.getContatos().getTipos());
        assertNotNull(fornecedorEntity.getContatos().getCreatedAt());
        assertNotNull(fornecedorEntity.getContatos().getCreatedBy());
        assertNotNull(fornecedorEntity.getContatos().getLastModifiedAt());
        assertNotNull(fornecedorEntity.getContatos().getLastModifiedBy());

        MatcherAssert.assertThat(fornecedorEntity.getContatos().getTipos().size(), Matchers.greaterThan(0));
        MatcherAssert.assertThat(fornecedorEntity.getContatos().getTipos().size(), Matchers.equalTo(5));

        assertNotNull(fornecedorEntity.getContatos().getTipos().get(0).getId());
        assertNotNull(fornecedorEntity.getContatos().getTipos().get(1).getId());
        assertNotNull(fornecedorEntity.getContatos().getTipos().get(2).getId());
        assertNotNull(fornecedorEntity.getContatos().getTipos().get(3).getId());
        assertNotNull(fornecedorEntity.getContatos().getTipos().get(4).getId());
    }
}