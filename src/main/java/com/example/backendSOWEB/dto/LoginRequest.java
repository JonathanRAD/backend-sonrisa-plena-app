package com.example.backendSOWEB.dto;
import lombok.*;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class LoginRequest { private String username; private String password; }