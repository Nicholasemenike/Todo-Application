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

    public ResponseEntity<String> newTask(Task task) {
        System.out.println(task);
        User user = userRepository.findByEmail(task.getUser().getEmail()).get();

        var newTask = new Task();
        newTask.setUser(user);
        newTask.setDescription(task.getDescription());
        newTask.setImportant(task.isImportant());
        newTask.setCompleted(task.isCompleted());
        newTask.setName(task.getName());
        newTask.setTime(task.getTime());
        taskRepository.save(newTask);
        return new ResponseEntity<>("succussfully added", HttpStatus.OK);
    }

    public List<Task> getListOfUndoneTask() {
        return taskRepository.getUndoneTask();
    }

    public List<Task> getAllTask(int id) {
        return taskRepository.findtaskbyid(id);
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
            if (user.getPassword().equals(Upasscode)) {
                return new ResponseEntity<>("Successfully logged in ", HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_GATEWAY);
            }
        } else {
            return new ResponseEntity<>("Account dont exists", HttpStatus.FORBIDDEN);
        }
    }
}
