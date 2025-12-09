package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.PageCacheDTO;
import br.com.exemplo.crudadvogado.core.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarClientesPaginadoUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private ProcessoGateway processoGateway;

    @InjectMocks
    private ListarClientesPaginadoUseCase useCase;

    private Cliente cliente;

    @BeforeEach
    void setup() {
        cliente = new Cliente();
        cliente.setIdCliente(UUID.randomUUID());
        cliente.setNome("João");
        cliente.setDocumento("12345678900");
        cliente.setTipoDocumento("CPF");
        cliente.setEmail(br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email.criar("joao@email.com"));
        cliente.setTelefone("11999999999");
        cliente.setEndereco("Rua A");
        cliente.setGenero(br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Genero.criar("MASCULINO"));
        cliente.setDataNascimento(LocalDate.of(1995, 5, 10));
        cliente.setEstadoCivil(br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.EstadoCivil.criar("SOLTEIRO"));
        cliente.setProfissao("Dev");
        cliente.setPassaporte("123");
        cliente.setCnh("456");
        cliente.setNaturalidade("SP");
        cliente.setIdAdvogado(UUID.randomUUID());
    }

    // ============================================
    // ✅ SUCESSO — LISTAGEM PAGINADA
    // ============================================
    @Test
    void deveListarClientesPaginados() {
        // ARRANGE
        Pageable pageable = PageRequest.of(0, 10, Sort.by("nome"));

        Page<Cliente> page =
                new PageImpl<>(List.of(cliente), pageable, 1);

        when(clienteGateway.listarPaginado(any(Pageable.class)))
                .thenReturn(page);

        when(processoGateway.contarProcessosPorCliente(cliente.getIdCliente()))
                .thenReturn(5L);

        // ACT
        Object result = useCase.executar(pageable);

        // ASSERT
        assertNotNull(result);
        assertTrue(result instanceof PageCacheDTO);

        PageCacheDTO<?> dto = (PageCacheDTO<?>) result;

        assertEquals(0, dto.getPage());
        assertEquals(10, dto.getSize());
        assertEquals(1, dto.getTotalElements());
        assertEquals(1, dto.getTotalPages());
        assertFalse(dto.isEmpty());
        assertEquals(1, dto.getContent().size());

        verify(clienteGateway, times(1))
                .listarPaginado(any(Pageable.class));

        verify(processoGateway, times(1))
                .contarProcessosPorCliente(cliente.getIdCliente());
    }

    // ============================================
    // ✅ PAGE SIZE NEGATIVO (PROTEÇÃO DO USECASE)
    // ============================================
    @Test
    void deveAjustarPageSizeQuandoForMenorQueUm() {
        // ARRANGE
        Pageable pageable = PageRequest.of(0, 0);

        Page<Cliente> page =
                new PageImpl<>(List.of(cliente));

        when(clienteGateway.listarPaginado(any(Pageable.class)))
                .thenReturn(page);

        when(processoGateway.contarProcessosPorCliente(any()))
                .thenReturn(1L);

        // ACT
        Object result = useCase.executar(pageable);

        // ASSERT
        assertNotNull(result);
        assertTrue(result instanceof PageCacheDTO);

        verify(clienteGateway)
                .listarPaginado(argThat(p -> p.getPageSize() == 1));
    }

    // ============================================
    // ✅ PAGE NUMBER NEGATIVO (PROTEÇÃO DO USECASE)
    // ============================================
    @Test
    void deveAjustarPageNumberQuandoForNegativo() {
        // ARRANGE
        Pageable pageable = PageRequest.of(-1, 5);

        Page<Cliente> page =
                new PageImpl<>(List.of(cliente));

        when(clienteGateway.listarPaginado(any(Pageable.class)))
                .thenReturn(page);

        when(processoGateway.contarProcessosPorCliente(any()))
                .thenReturn(1L);

        // ACT
        useCase.executar(pageable);

        // ASSERT
        verify(clienteGateway)
                .listarPaginado(argThat(p -> p.getPageNumber() == 0));
    }
}
