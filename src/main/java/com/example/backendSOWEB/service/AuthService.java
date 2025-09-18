package com.example.backendSOWEB.service;

import com.example.backendSOWEB.dto.AuthResponse;
import com.example.backendSOWEB.dto.LoginRequest;
import com.example.backendSOWEB.dto.RegisterRequest;
import com.example.backendSOWEB.model.Rol;
import com.example.backendSOWEB.model.Usuario;
import com.example.backendSOWEB.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private static final int MAX_INTENTOS_FALLIDOS = 3;
    private static final int TIEMPO_BLOQUEO_MINUTOS = 15;

    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));

        if (!usuario.isAccountNonLocked()) {
            throw new LockedException("La cuenta está bloqueada temporalmente.");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            usuario.setIntentosFallidos(0);
            usuario.setBloqueoExpiracion(null);
            usuarioRepository.save(usuario);

            String token = jwtService.generateToken(usuario);
            return AuthResponse.builder().token(token).build();
        } catch (AuthenticationException e) {
            usuario.setIntentosFallidos(usuario.getIntentosFallidos() + 1);
            if (usuario.getIntentosFallidos() >= MAX_INTENTOS_FALLIDOS) {
                usuario.setBloqueoExpiracion(LocalDateTime.now().plusMinutes(TIEMPO_BLOQUEO_MINUTOS));
            }
            usuarioRepository.save(usuario);
            throw new BadCredentialsException("Credenciales inválidas");
        }
    }

    public AuthResponse register(RegisterRequest request) {
        if (!esContrasenaSegura(request.getPassword())) {
            throw new IllegalArgumentException("La contraseña no cumple con los requisitos de seguridad.");
        }

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.PACIENTE)
                .intentosFallidos(0)
                .build();

        usuarioRepository.save(usuario);
        String token = jwtService.generateToken(usuario);
        return AuthResponse.builder().token(token).build();
    }

    private boolean esContrasenaSegura(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        // SOLUCIÓN: Se añade '#' a la lista de caracteres especiales permitidos
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$");
        return pattern.matcher(password).matches();
    }
}