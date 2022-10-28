package com.example.authservice.models.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "password")
    @NonNull
    private String password;

    @Column(name = "role_name")
    @NonNull
    private String roleName;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @Column(name = "birthday_date")
    @NonNull
    private LocalDate birthdayDate;

    public Account(@NonNull String email,
                   @NonNull String password,
                   @NonNull String roleName,
                   @NonNull String firstName,
                   @NonNull String lastName,
                   @NonNull LocalDate birthdayDate) {
        this.email = email;
        this.password = password;
        this.roleName = roleName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdayDate = birthdayDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && email.equals(account.email) && password.equals(account.password) && roleName.equals(account.roleName) && firstName.equals(account.firstName) && lastName.equals(account.lastName) && birthdayDate.equals(account.birthdayDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, roleName, firstName, lastName, birthdayDate);
    }
}
