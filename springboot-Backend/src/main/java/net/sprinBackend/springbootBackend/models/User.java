package net.sprinBackend.springbootBackend.models;

import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;
    private String name;

    @NaturalId(mutable = true)
    private String email;
    private String password;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.EAGER
    )
    private List<Task> tasks;

     @JsonCreator
    public static User fromJson(
            @JsonProperty("userId") int userId,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("tasks") List<Task> tasks) {

        return User.builder()
                .userId(userId)
                .name(name)
                .email(email)
                .password(password)
                .tasks(tasks)
                .build();
    }
}
