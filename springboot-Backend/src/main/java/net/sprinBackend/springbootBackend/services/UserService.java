package net.sprinBackend.springbootBackend.services;

import net.sprinBackend.springbootBackend.models.Task;
import net.sprinBackend.springbootBackend.repository.TaskRepository;
import net.sprinBackend.springbootBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getListOfCompletedTask(){
        return taskRepository.getCompletedTask();
    }
}
