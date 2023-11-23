package net.sprinBackend.springbootBackend.services.serviceImplementors;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.sprinBackend.springbootBackend.exception.UserAlreadyExistException;
import net.sprinBackend.springbootBackend.models.Task;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.repository.TaskRepository;
import net.sprinBackend.springbootBackend.repository.UserRepository;
import net.sprinBackend.springbootBackend.services.serviceInterface.UserService;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getListOfCompletedTask() {
        return taskRepository.getCompletedTask();
    }

    public ResponseEntity<String> newTask(String userid,Task task) {
        User user = userRepository.findByEmail(userid).get();

        User ownUser = User.builder()
        .userId(user.getUserId())
        .email(user.getEmail())
        .name(user.getName())
        .password(user.getPassword())
        .build();

        Task newTask = Task.builder()
        .user(ownUser)
        .completed(task.isCompleted())
        .description(task.getDescription())
        .name(task.getName())
        .time(task.getTime())
        .important(task.isImportant())
        .build();
        taskRepository.save(newTask);
        return new ResponseEntity<>("succussfully added", HttpStatus.OK);
    }

    public List<Task> getListOfUndoneTask() {
        return taskRepository.getUndoneTask();
    }

    public ResponseEntity<List<Task>>getAllTask(User id) {
        try{
            return taskRepository.findtaskbyid(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Optional<User> getProfile(int id) {
        return userRepository.findById((long) id);
    }

    public void deleteTask(int id, int taskid) {
        taskRepository.deleteById((long) id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(user.getEmail()+ " already exist");
        }
        var newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setTasks(user.getTasks());
        return userRepository.save(newUser);
    }

    @Override
    public ResponseEntity<String> login(User user) {
        var U = userRepository.findByEmail(user.getEmail());
        if (U.isPresent()) {
            var Upasscode = U.get().getPassword();
            if (passwordEncoder.matches(user.getPassword(), Upasscode)) {
                return new ResponseEntity<>("200", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("302", HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("502", HttpStatus.OK);
        }
    }
}
