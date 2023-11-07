package net.sprinBackend.springbootBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Long userId;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Task>  tasks;
}
