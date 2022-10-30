package com.example.authservice.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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

    public Account(@NonNull String email,
                   @NonNull String password,
                   @NonNull String roleName,
                   @NonNull Boolean enable) {
        this.email = email;
        this.password = password;
        this.roleName = roleName;
        this.enable = enable;
    }

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
