package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database;

import io.github.edsongustavotofolo.microservicetemplate.infrastructure.configuration.database.repository.AuditConfig;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.FornecedorEntity;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository.CidadeJpaRepository;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository.FornecedorJpaRepository;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static io.github.edsongustavotofolo.microservicetemplate.domain.builder.FornecedorBuilder.umFornecedorSemId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@DataJpaTest
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@EntityScan
@ContextConfiguration(classes = AuditConfig.class)
@TestPropertySource("classpath:application.properties")
class FornecedorJpaProviderIntegrationTest {

    @SpyBean
    private FornecedorJpaRepository fornecedorJpaRepository;
    @Autowired
    private CidadeJpaRepository cidadeJpaRepository;

    @Captor
    private ArgumentCaptor<FornecedorEntity> argumentCaptor;

    private FornecedorProvider fornecedorProvider;

    @BeforeEach
    public void setup() {
        this.fornecedorProvider = new FornecedorJpaProvider(this.fornecedorJpaRepository, this.cidadeJpaRepository);
    }

    @Test
    void deveDeletarFornecedorComSucesso() {
        final var fornecedor = umFornecedorSemId().build();

        final var id = this.fornecedorProvider.create(fornecedor);

        this.fornecedorProvider.delete(id);

        final var existsFornecedor = this.fornecedorProvider.existsFornecedorById(id);

        assertThat(existsFornecedor).isFalse();
    }

    @Test
    void deveAtualizarFornecedorComSucesso() {
        // cenario
        final var fornecedor = umFornecedorSemId().build();

        final var id = this.fornecedorProvider.create(fornecedor);

        fornecedor.setId(id);
        fornecedor.setRazaoSocial("Nova Razao Social");

        // execucao
        this.fornecedorProvider.update(fornecedor);

        // verificacao
        final var entity = this.fornecedorProvider.getById(id).get();

        assertThat(entity.getRazaoSocial()).isEqualTo(fornecedor.getRazaoSocial());
    }

    @Test
    void deveRetornarTrueAoVerificarExistenciaDeFornecedorPorCnpj() {
        // cenario
        final var fornecedor = umFornecedorSemId().build();
        this.fornecedorProvider.create(fornecedor);

        // exec
        final var actualExistsByCnpj = this.fornecedorProvider.existsFornecedorWithCnpj(fornecedor.getCnpj().toString());

        // check
        assertTrue(actualExistsByCnpj);
    }

    @Test
    void deveCriarFornecedorComSucesso() {
        // cenario
        final var fornecedor = umFornecedorSemId().build();

        // execucao
        final var id = this.fornecedorProvider.create(fornecedor);

        // verificacao
        assertNotNull(id);

        verify(this.fornecedorJpaRepository).persist(this.argumentCaptor.capture());

        final var actualFornecedorEntity = this.argumentCaptor.getValue();

        assertThat(actualFornecedorEntity).isNotNull();
        assertThat(actualFornecedorEntity.getId()).isNotNull();

        assertEquals(id, actualFornecedorEntity.getId());

        assertNotNull(actualFornecedorEntity.getCreatedAt());
        assertNotNull(actualFornecedorEntity.getCreatedBy());
        assertNotNull(actualFornecedorEntity.getLastModifiedAt());
        assertNotNull(actualFornecedorEntity.getLastModifiedBy());

        assertNotNull(actualFornecedorEntity.getEndereco());
        assertNotNull(actualFornecedorEntity.getEndereco().getId());
        assertNotNull(actualFornecedorEntity.getEndereco().getCreatedAt());
        assertNotNull(actualFornecedorEntity.getEndereco().getCreatedBy());
        assertNotNull(actualFornecedorEntity.getEndereco().getLastModifiedAt());
        assertNotNull(actualFornecedorEntity.getEndereco().getLastModifiedBy());

        assertNotNull(actualFornecedorEntity.getContatos());
        assertNotNull(actualFornecedorEntity.getContatos().getId());
        assertNotNull(actualFornecedorEntity.getContatos().getTipos());
        assertNotNull(actualFornecedorEntity.getContatos().getCreatedAt());
        assertNotNull(actualFornecedorEntity.getContatos().getCreatedBy());
        assertNotNull(actualFornecedorEntity.getContatos().getLastModifiedAt());
        assertNotNull(actualFornecedorEntity.getContatos().getLastModifiedBy());

        MatcherAssert.assertThat(actualFornecedorEntity.getContatos().getTipos().size(), Matchers.greaterThan(0));
        MatcherAssert.assertThat(actualFornecedorEntity.getContatos().getTipos().size(), Matchers.equalTo(5));

        assertNotNull(actualFornecedorEntity.getContatos().getTipos().get(0).getId());
        assertNotNull(actualFornecedorEntity.getContatos().getTipos().get(1).getId());
        assertNotNull(actualFornecedorEntity.getContatos().getTipos().get(2).getId());
        assertNotNull(actualFornecedorEntity.getContatos().getTipos().get(3).getId());
        assertNotNull(actualFornecedorEntity.getContatos().getTipos().get(4).getId());
    }
}