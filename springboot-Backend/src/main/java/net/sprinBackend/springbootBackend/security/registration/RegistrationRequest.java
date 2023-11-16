package net.sprinBackend.springbootBackend.security.registration;


public record RegistrationRequest(
        String name,
        String email,
        String password,
        String role
        ) {
}
