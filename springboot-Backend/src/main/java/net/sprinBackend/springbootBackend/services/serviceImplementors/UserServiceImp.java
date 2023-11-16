package net.sprinBackend.springbootBackend.services.serviceImplementors;

import net.sprinBackend.springbootBackend.models.Task;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.repository.TaskRepository;
import net.sprinBackend.springbootBackend.repository.UserRepository;
import net.sprinBackend.springbootBackend.services.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private TaskRepository taskRepository;

    public List<Task> getListOfCompletedTask(){
        return taskRepository.getCompletedTask();
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

    @Override
    public void saveUserVerificationToken(User user, String token) {

    }

    public List<User> findByEmail(String email){
        return userRepository.findByEmail(email);
  }

}
