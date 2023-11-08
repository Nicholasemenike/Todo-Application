package net.sprinBackend.springbootBackend.repository;

import net.sprinBackend.springbootBackend.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.completed = true")
    List<Task> getCompletedTask();

    @Query("SELECT t FROM Task t WHERE t.completed = false")
    List<Task> getUndoneTask();
}
