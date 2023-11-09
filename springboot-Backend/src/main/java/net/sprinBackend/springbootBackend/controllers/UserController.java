package net.sprinBackend.springbootBackend.controllers;

import net.sprinBackend.springbootBackend.models.Task;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/task/completed")
    public List<Task> getCompletedTask(){
        return userService.getListOfCompletedTask();
    }

    @GetMapping("/task/undone")
    public List<Task> getUndoneTask(){return userService.getListOfUndoneTask();}

    @PostMapping("/adduser")
    public String saveUser(@RequestBody User user){
        try{
            userService.SaveUser(user);
            return "successfully Registered..";
        }catch (Exception e) {
            return "contact admin";
        }
    }

    @PostMapping("/addtask")
    public String saveNewTask(@RequestBody Task task){
        try{
            userService.SaveNewTask(task);
            return "Successfully added";
        }catch (Exception e){
            return "contact N--k__Y";
        }
    }

    @GetMapping("/alltask")
    public List<Task> getAllTask(){
        return userService.getAllTask();
    }

    @GetMapping("/profile/{id}")
    public User userProfile(@PathVariable("id") int id){
        return userService.getProfile(id);
    }

    @DeleteMapping("/deletetask/{id}")
    public String deleteTask(@PathVariable("id") int id){
        try{
            userService.deleteTask(id);
            return "Successfully Deleted";
        }catch (Exception e){
            return "Failed!! Contact Nky ";
        }
    }
}
