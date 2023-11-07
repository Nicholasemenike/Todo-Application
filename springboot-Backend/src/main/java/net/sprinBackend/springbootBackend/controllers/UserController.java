package net.sprinBackend.springbootBackend.controllers;

import net.sprinBackend.springbootBackend.models.Task;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = "/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/completed")
    public List<Task> getCompletedTask(){
        return userService.getListOfCompletedTask();
    }

    @PostMapping("/newUser")
    public String saveUser(User user){
        try{
            userService.SaveUser(user);
            return "successfully Registered..";
        }catch (Exception e) {
            e.printStackTrace();
            return "contact admin";
        }
    }

    @PostMapping("/newTask")
    public String saveNewTask(Task task){
        try{
            userService.SaveNewTask(task);
            return "Successfully added";
        }catch (Exception e){
            e.printStackTrace();
            return "contact N--k__Y";
        }
    }
}
