package engine.spectro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="supplier")
public class EmployeeEntity {
    @Id
    @Column
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    @Column(name="email")
    private String email;

    @Getter
    @Setter
    @Column(name="number")
    private String numberphone;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    @Getter
    @Setter
    @Column(name="company")
    private String company;

    @Getter
    @Setter
    @Column(name="role")
    private String role;
}
