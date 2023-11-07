package net.sprinBackend.springbootBackend.repository;

import net.sprinBackend.springbootBackend.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM task t WHERE t.completed = true")
    List<Task> getCompletedTask();
}
