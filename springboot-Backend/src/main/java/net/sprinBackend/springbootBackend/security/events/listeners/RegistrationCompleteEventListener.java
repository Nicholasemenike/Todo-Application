package net.sprinBackend.springbootBackend.security.events.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.security.events.RegistrationCompleteEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener  implements ApplicationListener<RegistrationCompleteEvent> {
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //get the newly registered user
        User user = event.getUser();
        //create a verification token for the user
        String verificationToken = UUID.randomUUID().toString();
        //save the verification token for the user

        //build the verification url to be sent to the user
        String url = event.getApplicationUrl()+"/user/verifyEmail?token="+verificationToken;
        //send the email to the user
        log.info("click the link to verify user registration : {}", url);

    }
}
