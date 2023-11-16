package net.sprinBackend.springbootBackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import net.sprinBackend.springbootBackend.models.Task;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.repository.UserRepository;
import net.sprinBackend.springbootBackend.security.events.RegistrationCompleteEvent;
import net.sprinBackend.springbootBackend.security.exceptions.UserAlreadyExistException;
import net.sprinBackend.springbootBackend.security.registration.RegistrationRequest;
import net.sprinBackend.springbootBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping(path = "/user")
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;

//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody User user){
//        if (checkUser(user.getUserId())){
//            if(checkLogin(user)){
//                return new ResponseEntity<>("Successfully login", HttpStatus.OK);
//            }
//        }
//
//    }

    @GetMapping("/task/completed")
    public List<Task> getCompletedTask(){
        return userService.getListOfCompletedTask();
    }

    @GetMapping("/task/undone")
    public List<Task> getUndoneTask(){return userService.getListOfUndoneTask();}

    @PostMapping("/register")
    public String saveUser(@RequestBody User user, final HttpServletRequest request){
        try{
            userService.SaveUser(user);
            publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request )));
            return "Success, check email to complete registration...";
        }catch (Exception e) {
            return "contact admin";
        }
    }

    @GetMapping("/verifyEmail")
    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
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

    @GetMapping("/allusers")
    public ResponseEntity< List<User>>getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
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
