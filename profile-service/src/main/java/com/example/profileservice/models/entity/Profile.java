package com.example.profileservice.models.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_id")
    @NonNull
    private Long accountId;
    @Column(name = "first_name")
    @NonNull
    private String firstName;
    @Column(name = "last_name")
    @NonNull
    private String lastName;
    @Column(name = "birthday_date")
    @Type(type = "org.hibernate.type.LocalDateType")
    @NonNull
    private LocalDate birthdayDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) && accountId.equals(profile.accountId) && firstName.equals(profile.firstName) && lastName.equals(profile.lastName) && birthdayDate.equals(profile.birthdayDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, firstName, lastName, birthdayDate);
    }
}
