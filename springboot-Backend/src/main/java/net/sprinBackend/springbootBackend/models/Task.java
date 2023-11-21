package net.sprinBackend.springbootBackend.models;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;
    private String name;
    private String description;
    private Date time;
    @Value("false")
    private boolean important;
    @Value("false")
    private boolean completed;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            insertable = false,
            updatable = false
    )
    private User user;

}
