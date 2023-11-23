package net.sprinBackend.springbootBackend.services.serviceInterface;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import net.sprinBackend.springbootBackend.models.Task;
import net.sprinBackend.springbootBackend.models.User;

public interface UserService {

    List<Task> getListOfCompletedTask();

    ResponseEntity<String> newTask(String usreid,Task task);

    List<Task> getListOfUndoneTask();

    Optional<User> getProfile(int id);

    void deleteTask(int id, int userid);

    List<User> getAllUsers();

    User register(User user);

    ResponseEntity<String> login(User user);
}
