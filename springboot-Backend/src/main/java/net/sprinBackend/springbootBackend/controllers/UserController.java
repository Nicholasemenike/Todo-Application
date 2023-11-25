package net.sprinBackend.springbootBackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sprinBackend.springbootBackend.exception.UserAlreadyExistException;
import net.sprinBackend.springbootBackend.models.Task;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.services.serviceImplementors.UserServiceImp;

@RequestMapping(path = "/user")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/task/completed/{id}")
    public List<Task> getCompletedTask(@PathVariable int id) {
        return userServiceImp.getListOfCompletedTask();
    }

    @GetMapping("/task/undone")
    public List<Task> getUndoneTask() {
        return userServiceImp.getListOfUndoneTask();
    }


    @PostMapping("task/add/{userid}")
    public ResponseEntity<?> saveNewTask(@PathVariable String userid, @RequestBody Task task) {
        try {
            return userServiceImp.newTask(userid,task);
        } catch (Exception e) {
            return new ResponseEntity<>("contact n__k--y", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("task/all/{userid}")
    public ResponseEntity<List<Task>> getAllTask(@PathVariable String userid) {
        try{
            return userServiceImp.getAllTask(userid);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userServiceImp.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public Optional<User> userProfile(@PathVariable("id") int id) {
        return userServiceImp.getProfile(id);
    }

    @DeleteMapping("task/delete/{taskid}/{userid}")
    public String deleteTask(@PathVariable("id") int taskid, int userid) {
        try {
            userServiceImp.deleteTask(userid, taskid);
            return "Successfully Deleted";
        } catch (Exception e) {
            return "Failed!! Contact Nky ";
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try{
            return ResponseEntity.ok(userServiceImp.register(user));
        }catch (UserAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
            return userServiceImp.login(user);
    }
}
