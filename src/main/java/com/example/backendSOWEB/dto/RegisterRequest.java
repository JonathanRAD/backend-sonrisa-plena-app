package com.example.backendSOWEB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username; // Correo electrónico
    private String password;
    private String nombre;
    private String apellido;
}