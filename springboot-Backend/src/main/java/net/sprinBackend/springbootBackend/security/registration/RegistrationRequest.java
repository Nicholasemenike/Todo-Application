package net.sprinBackend.springbootBackend.security.registration;

import org.hibernate.annotations.NaturalId;

public record RegistrationRequest(
        String name,
        String email,
        String password,
        String role
        ) {
}
