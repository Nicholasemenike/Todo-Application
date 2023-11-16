package net.sprinBackend.springbootBackend.security.events.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.security.events.RegistrationCompleteEvent;
import net.sprinBackend.springbootBackend.services.serviceImplementors.UserServiceImp;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener  implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserServiceImp userServiceImp;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // 1. get the newly registered user
        User user = event.getUser();
        // 2. create a verification token for the user
        String verificationToken = UUID.randomUUID().toString();
        // 3. save the verification token for the user
        userServiceImp.saveUserVerificationToken(user, verificationToken);
        // 4. build the verification url to be sent to the user
        String url = event.getApplicationUrl()+"/user/verifyEmail?token="+verificationToken;
        // 5. send the email to the user
        log.info("click the link to verify user registration : {}", url);

    }
}
