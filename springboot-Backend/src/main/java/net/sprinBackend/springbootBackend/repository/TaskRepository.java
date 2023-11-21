package net.sprinBackend.springbootBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.sprinBackend.springbootBackend.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.completed = true")
    List<Task> getCompletedTask();

    @Query("SELECT t FROM Task t WHERE t.completed = false")
    List<Task> getUndoneTask();

    @Query(nativeQuery = true, value = "SELECT * FROM task t WHERE t.user_id = ?1")
    List<Task> findtaskbyid(int id);
}
