package com.example.backendSOWEB.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @GetMapping("/publico")
    public String endpointPublico() {
        return "Este es un endpoint PÚBLICO.";
    }

    @GetMapping("/pacientes")
    @PreAuthorize("hasAuthority('PACIENTE')")
    public String endpointPacientes() {
        return "Hola PACIENTE, bienvenido a tu portal.";
    }

    @GetMapping("/odontologos")
    @PreAuthorize("hasAuthority('ODONTOLOGO')")
    public String endpointOdontologos() {
        return "Hola ODONTÓLOGO, aquí están tus herramientas.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String endpointAdmin() {
        return "Hola ADMIN, tienes acceso a todo el sistema.";
    }
}