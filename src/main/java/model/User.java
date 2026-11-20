package model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity( name="usertable")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fn;
    private String ln;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private UserRole userRole=UserRole.ADMIN;


   /* public User() {
    }

    public User(Long id, String fn, String ln) {
        this.id = id;
        this.fn = fn;
        this.ln = ln;
    }*/
}
