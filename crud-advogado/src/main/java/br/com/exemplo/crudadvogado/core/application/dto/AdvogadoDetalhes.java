package br.com.exemplo.crudadvogado.core.application.dto;

import br.com.exemplo.crudadvogado.infraestructure.persistence.jpa.entity.AdvogadoEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;

public class AdvogadoDetalhes implements UserDetails {
    private final AdvogadoEntity advogado;

    public AdvogadoDetalhes(AdvogadoEntity advogado) {
        this.advogado = advogado;
    }

    public String getNome() {
        return advogado.getNome();
    }

    public String getEmail() {
        return advogado.getEmail();
    }

    public String getSenha() {
        return advogado.getSenha();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADVOGADO"));
    }

    @Override
    public String getPassword() {
        return advogado.getSenha();
    }

    @Override
    public String getUsername() {
        return advogado.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public AdvogadoEntity getAdvogado() {
        return advogado;
    }
}
