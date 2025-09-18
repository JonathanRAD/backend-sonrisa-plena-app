package com.example.backendSOWEB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    @Column(nullable = false)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    // Campos para bloqueo de cuenta
    private int intentosFallidos;
    private LocalDateTime bloqueoExpiracion;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (bloqueoExpiracion == null) {
            return true;
        }
        return bloqueoExpiracion.isBefore(LocalDateTime.now());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}