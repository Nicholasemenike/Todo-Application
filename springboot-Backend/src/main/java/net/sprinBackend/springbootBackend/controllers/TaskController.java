package net.sprinBackend.springbootBackend.controllers;

import net.sprinBackend.springbootBackend.models.Task;
import net.sprinBackend.springbootBackend.services.TaskService;
import net.sprinBackend.springbootBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/task")
public class TaskController {
}
