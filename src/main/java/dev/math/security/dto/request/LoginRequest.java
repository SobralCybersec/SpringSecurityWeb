package dev.math.security.dto.request;


import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(@NotEmpty(message = "E-mail é obrigatório") String email, @NotEmpty(message = "senha é obrigatória") String password) {


}
