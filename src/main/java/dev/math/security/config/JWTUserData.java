package dev.math.security.config;

import lombok.Builder;

@Builder
public record JWTUserData(long userId, String email) {
}
