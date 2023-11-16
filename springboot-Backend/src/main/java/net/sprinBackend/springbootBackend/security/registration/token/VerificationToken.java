package net.sprinBackend.springbootBackend.security.registration.token;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sprinBackend.springbootBackend.models.User;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class VerificationToken {
    private static  final int ExpirationTime = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationDate;

    @OneToOne
    @JoinColumn(name =  "userId")
    private User user;

    public VerificationToken(String token, User user) {
        super();
        this.token = token;
        this.user = user;
        this.expirationDate = this.getTokenExpirationTime();
    }

    public VerificationToken(String token){
        super();
        this.token = token;
        this.expirationDate = this.getTokenExpirationTime();
    }

    private Date getTokenExpirationTime() {
        Calendar calendar =Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, ExpirationTime);
        return new Date(calendar.getTime().getTime());
    }
}
