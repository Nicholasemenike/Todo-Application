package net.sprinBackend.springbootBackend.services.serviceImplementors;

import net.sprinBackend.springbootBackend.models.Task;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.repository.TaskRepository;
import net.sprinBackend.springbootBackend.repository.UserRepository;
import net.sprinBackend.springbootBackend.repository.VerificationTokenRepository;
import net.sprinBackend.springbootBackend.security.registration.RegistrationRequest;
import net.sprinBackend.springbootBackend.security.registration.token.VerificationToken;
import net.sprinBackend.springbootBackend.services.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import net.sprinBackend.springbootBackend.security.exceptions.UserAlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserRepository userRepository;
    @Autowired private TaskRepository taskRepository;
    @Autowired private VerificationTokenRepository verificationTokenRepository;

    public List<Task> getListOfCompletedTask(){
        return taskRepository.getCompletedTask();
    }

    public User registerUser(RegistrationRequest request) {
        Optional <List<User>> user = Optional.ofNullable(this.findByEmail(request.email()));
        if(user.isPresent()){
            throw new UserAlreadyExistException("Account with email "+request.email()+" already Exist...");
        }
        var newUser = new User();
        newUser.setName(request.name());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(request.role());
        return userRepository.save(newUser);
    }

    public void newTask(Task task){
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

    public List<User> findByEmail(String email){
        return userRepository.findByEmail(email);
  }

    public void saveUserVerificationToken(User user, String token) {
        var verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
    }
}
