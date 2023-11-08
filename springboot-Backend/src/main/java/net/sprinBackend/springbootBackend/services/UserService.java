package net.sprinBackend.springbootBackend.services;

import net.sprinBackend.springbootBackend.models.Task;
import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.repository.TaskRepository;
import net.sprinBackend.springbootBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getListOfCompletedTask(){
        return taskRepository.getCompletedTask();
    }

    public void SaveUser(User user) {
        userRepository.save(user);
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
}
