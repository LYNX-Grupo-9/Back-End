package br.com.exemplo.crudadvogado.core.application.usecase.advogado;

import br.com.exemplo.crudadvogado.core.application.dto.response.advogado.AdvogadoDetalhes;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.AdvogadoJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutenticacaoUseCaseTest {

    @Mock
    private AdvogadoJpaRepository repository;

    @InjectMocks
    private AutenticacaoUseCase autenticacaoUseCase;

    private AdvogadoEntity advogado;

    @BeforeEach
    void setup() {
        advogado = new AdvogadoEntity();
        advogado.setId(1L);
        advogado.setEmail("teste@email.com");
        advogado.setSenha("123456");
    }

    @Test
    void deveRetornarUserDetailsQuandoUsuarioExiste() {
        // ARRANGE
        when(repository.findByEmail("teste@email.com"))
                .thenReturn(Optional.of(advogado));

        // ACT
        UserDetails userDetails =
                autenticacaoUseCase.loadUserByUsername("teste@email.com");

        // ASSERT
        assertNotNull(userDetails);
        assertTrue(userDetails instanceof AdvogadoDetalhes);
        assertEquals("teste@email.com", userDetails.getUsername());

        verify(repository, times(1))
                .findByEmail("teste@email.com");
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExiste() {
        // ARRANGE
        when(repository.findByEmail("naoexiste@email.com"))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> autenticacaoUseCase.loadUserByUsername("naoexiste@email.com")
        );

        assertEquals("Usuário não encontrado", exception.getMessage());

        verify(repository, times(1))
                .findByEmail("naoexiste@email.com");
    }
}
