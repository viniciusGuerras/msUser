package com.compassuol.sp.challenge.msuser.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Entity
@Table(schema = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 120, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 120, nullable = false)
    private String lastName;

    @Column(name = "cpf", unique = true, length = 11, nullable = false)
    private String cpf;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @Column(name = "email", length = 120, nullable = false, unique = true)
    private String email;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "password", length = 120, nullable = false)
    private String password;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
