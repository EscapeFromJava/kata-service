package com.example.authservice.models.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true)
    @NonNull
    @Email
    private String email;

    @Column(name = "password")
    @NonNull
    private String password;

    @Column(name = "role_name")
    @NonNull
    private String roleName;

    @Column(name = "enable")
    @NonNull
    private Boolean enable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && email.equals(account.email) && password.equals(account.password) && roleName.equals(account.roleName) && enable.equals(account.enable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, roleName, enable);
    }
}
