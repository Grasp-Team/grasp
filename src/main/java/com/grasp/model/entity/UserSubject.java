package com.grasp.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_subjects", schema = "course")
public class UserSubject {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "uid")
    private UUID userId;
    @Column(name="subject")
    private String subject;

    public UserSubject() {
    }

    public UserSubject(UUID userId, String subject) {
        this.userId = userId;
        this.subject = subject;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserSubject)) {
            return false;
        }

        UserSubject that = (UserSubject) o;

        if (!getId().equals(that.getId())) {
            return false;
        }
        if (!getUserId().equals(that.getUserId())) {
            return false;
        }
        return getSubject().equals(that.getSubject());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getUserId().hashCode();
        result = 31 * result + getSubject().hashCode();
        return result;
    }
}
