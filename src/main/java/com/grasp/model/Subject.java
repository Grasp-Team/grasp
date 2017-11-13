package com.grasp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "subjects", schema = "course")
public class Subject {

    @Id
    @Column(name = "subject")
    private String subject;

    public Subject() {
    }

    public Subject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Subject)) {
            return false;
        }

        Subject subject1 = (Subject) o;

        return getSubject().equals(subject1.getSubject());
    }

    @Override
    public int hashCode() {
        return getSubject().hashCode();
    }
}
