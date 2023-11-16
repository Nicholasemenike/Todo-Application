package net.sprinBackend.springbootBackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.security.events.RegistrationCompleteEvent;
import net.sprinBackend.springbootBackend.security.registration.RegistrationRequest;
import net.sprinBackend.springbootBackend.services.serviceImplementors.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "/register")
public class registerController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    public String saveUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){
        try{
            User user = userServiceImp.registerUser(registrationRequest);
            //public the event to the email
            publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request )));
            return "Success, check email to complete registration...";
        }catch (Exception e) {
            return "contact admin";
        }
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

}
