package com.grasp.model;

import com.grasp.security.model.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "users")
public class User {

    public enum UserType {
        STANDARD, TUTOR
    }

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
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "year", nullable = false)
    private int year;
    @Column(name = "program")
    private String program;
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.STANDARD;
    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.STANDARD;
    @JoinColumn(name = "uid")
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Tutor> tutors = new ArrayList<>();
    @JoinColumn(name = "uid")
    @OneToMany(cascade = {CascadeType.ALL})
    private List<UserSubject> subjects = new ArrayList<>();

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, int year, String program,
                String faculty, UserType userType, UserRole userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.year = year;
        this.program = program;
        this.faculty = faculty;
        this.userType = userType;
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (getYear() != user.getYear()) {
            return false;
        }
        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) {
            return false;
        }
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null) {
            return false;
        }
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null) {
            return false;
        }
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) {
            return false;
        }
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null) {
            return false;
        }
        if (getProgram() != null ? !getProgram().equals(user.getProgram()) : user.getProgram() != null) {
            return false;
        }
        if (getFaculty() != null ? !getFaculty().equals(user.getFaculty()) : user.getFaculty() != null) {
            return false;
        }
        if (getUserType() != user.getUserType()) {
            return false;
        }
        if (getUserRole() != user.getUserRole()) {
            return false;
        }
        if (getTutors() != null ? !getTutors().equals(user.getTutors()) : user.getTutors() != null) {
            return false;
        }
        return getSubjects() != null ? getSubjects().equals(user.getSubjects()) : user.getSubjects() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + getYear();
        result = 31 * result + (getProgram() != null ? getProgram().hashCode() : 0);
        result = 31 * result + (getFaculty() != null ? getFaculty().hashCode() : 0);
        result = 31 * result + (getUserType() != null ? getUserType().hashCode() : 0);
        result = 31 * result + (getUserRole() != null ? getUserRole().hashCode() : 0);
        result = 31 * result + (getTutors() != null ? getTutors().hashCode() : 0);
        result = 31 * result + (getSubjects() != null ? getSubjects().hashCode() : 0);
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
                ", userType=" + userType +
                ", tutors=" + tutors +
                '}';
    }
}
