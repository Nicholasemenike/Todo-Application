package net.sprinBackend.springbootBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Documented;
import java.util.Date;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @SequenceGenerator(
            name = "sequence_generator",
            sequenceName = "sequence_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "sequence_generator"
    )
    private Long taskId;
    private String name;
    private String description;
    private Date time;
    private boolean completed;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            insertable = false,
            updatable = false
    )
    private User user;

}
