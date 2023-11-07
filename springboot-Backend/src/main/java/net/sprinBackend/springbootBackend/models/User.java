package net.sprinBackend.springbootBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private int userId;
    
    @Column(
            nullable = false
    )
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Task>  tasks;
}
