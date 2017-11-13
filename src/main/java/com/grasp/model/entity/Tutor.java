package com.grasp.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "tutors", schema = "users")
public class Tutor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tutorId;
    @Column(name = "uid")
    private UUID uid;
    @ManyToOne
    @JoinColumn(name = "course_code")
    private CourseCatalog courseCatalog;

    public Tutor() {
    }

    public Tutor(UUID uid, CourseCatalog courseCatalog) {
        this.uid = uid;
        this.courseCatalog = courseCatalog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tutor)) {
            return false;
        }

        Tutor tutor = (Tutor) o;

        if (!getTutorId().equals(tutor.getTutorId())) {
            return false;
        }
        if (getUid() != null ? !getUid().equals(tutor.getUid()) : tutor.getUid() != null) {
            return false;
        }
        return getCourseCatalog().equals(tutor.getCourseCatalog());
    }

    @Override
    public int hashCode() {
        int result = getTutorId().hashCode();
        result = 31 * result + (getUid() != null ? getUid().hashCode() : 0);
        result = 31 * result + getCourseCatalog().hashCode();
        return result;
    }
}
