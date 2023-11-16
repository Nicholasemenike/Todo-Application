package net.sprinBackend.springbootBackend.services;

import lombok.RequiredArgsConstructor;
import net.sprinBackend.springbootBackend.models.Task;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.repository.TaskRepository;
import net.sprinBackend.springbootBackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import net.sprinBackend.springbootBackend.security.exceptions.UserAlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public List<Task> getListOfCompletedTask(){
        return taskRepository.getCompletedTask();
    }

    public User SaveUser(User user) throws UserAlreadyExistException {
        Optional<User> user1 = Optional.ofNullable(userRepository.findByEmail(user.email()));
        if(user1.isPresent()){
            throw new UserAlreadyExistException("Account with email "+user.email()+" already Exist...");
        }
        var newUser = new User();
        newUser.setName(user.name());
        newUser.setEmail(user.email());
        newUser.setPassword(passwordEncoder.encode(user.password()));
        newUser.setRole(user.role());
        return userRepository.save(newUser);
    }

    public void SaveNewTask(Task task){
        taskRepository.save(task);
    }

    public List<Task> getListOfUndoneTask() {
        return taskRepository.getUndoneTask();
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public User getProfile(int id) {
        return userRepository.findById((long) id).get();
    }

    public void deleteTask(int id) {
        taskRepository.deleteById((long) id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String registerUser(User user) {
        try{
            userRepository.save(user);
            return "Registered Successfully";
        }catch (Exception e){
            return "Registration Failed";
        }
    }


  public User findByEmail(String email){
        return userRepository.findByEmail(email);
  }
}
