package com.grasp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="users", schema="users")
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id")
    private UUID id;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "year", nullable = false)
    private int year;
    @Column(name = "program")
    private String program;
    @Column(name = "faculty")
    private String faculty;
    @JoinColumn(name = "uid")
    @OneToMany(cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<Tutor> tutors = new ArrayList<>();

    public User() {
    }

    public User(String firstName, String lastName, String email, int year, String program, String faculty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.year = year;
        this.program = program;
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (year != user.year) return false;
        if (!id.equals(user.id)) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!email.equals(user.email)) return false;
        if (program != null ? !program.equals(user.program) : user.program != null) return false;
        if (faculty != null ? !faculty.equals(user.faculty) : user.faculty != null) return false;
        return tutors.equals(user.tutors);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + year;
        result = 31 * result + (program != null ? program.hashCode() : 0);
        result = 31 * result + (faculty != null ? faculty.hashCode() : 0);
        result = 31 * result + tutors.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", year=" + year +
                ", program='" + program + '\'' +
                ", faculty='" + faculty + '\'' +
                ", tutors=" + tutors +
                '}';
    }
}
