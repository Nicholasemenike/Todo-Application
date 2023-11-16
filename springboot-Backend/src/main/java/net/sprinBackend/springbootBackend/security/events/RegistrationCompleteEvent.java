package net.sprinBackend.springbootBackend.security.events;

import lombok.Getter;
import lombok.Setter;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.security.registration.RegistrationRequest;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private User user;
    private String applicationUrl;

    public RegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }

}
