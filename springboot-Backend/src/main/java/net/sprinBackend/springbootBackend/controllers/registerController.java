package net.sprinBackend.springbootBackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import net.sprinBackend.springbootBackend.models.User;
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



    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

}
