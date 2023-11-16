package net.sprinBackend.springbootBackend.security.registration.service;

import net.sprinBackend.springbootBackend.repository.UserRepository;
import net.sprinBackend.springbootBackend.security.registration.details.UserRegistrationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .stream().map(UserRegistrationDetails::new)
                .findAny().orElseThrow(() -> new UsernameNotFoundException("User Not Found..."));
    }
}
