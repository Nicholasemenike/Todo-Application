package net.sprinBackend.springbootBackend.services.serviceInterface;


import net.sprinBackend.springbootBackend.models.Task;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.security.registration.RegistrationRequest;

import java.util.List;

public interface UserService{

    List<Task> getListOfCompletedTask();

    User registerUser(RegistrationRequest user);

    void newTask(Task task);

    List<Task> getListOfUndoneTask();

    List<Task> getAllTask();

    User getProfile(int id);

    void deleteTask(int id);

    List<User> getAllUsers();
    
    void saveUserVerificationToken(User user, String token);
}
