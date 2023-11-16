package net.sprinBackend.springbootBackend.controllers;

import lombok.RequiredArgsConstructor;
import net.sprinBackend.springbootBackend.models.Task;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.services.serviceImplementors.UserServiceImp;
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

    private final UserServiceImp userServiceImp;
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
        return userServiceImp.getListOfCompletedTask();
    }

    @GetMapping("/task/undone")
    public List<Task> getUndoneTask(){return userServiceImp.getListOfUndoneTask();}


    @PostMapping("/addtask")
    public String saveNewTask(@RequestBody Task task){
        try{
            userServiceImp.newTask(task);
            return "Successfully added";
        }catch (Exception e){
            return "contact N--k__Y";
        }
    }

    @GetMapping("/alltask")
    public List<Task> getAllTask(){
        return userServiceImp.getAllTask();
    }

    @GetMapping("/allusers")
    public ResponseEntity< List<User>>getAllUsers() {
        return new ResponseEntity<>(userServiceImp.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public User userProfile(@PathVariable("id") int id){
        return userServiceImp.getProfile(id);
    }

    @DeleteMapping("/deletetask/{id}")
    public String deleteTask(@PathVariable("id") int id){
        try{
            userServiceImp.deleteTask(id);
            return "Successfully Deleted";
        }catch (Exception e){
            return "Failed!! Contact Nky ";
        }
    }
}
