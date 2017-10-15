package com.grasp.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="tutors", schema="users")
public class Tutor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long tutorId;

    @Column(name = "uid")
    private UUID uid;

    @ManyToOne
    @JoinColumn(name = "course_id")
    CourseCatalog courseCatalog;

    public Tutor() {
    }

    public Tutor(UUID uid, CourseCatalog courseCatalog) {
        this.uid = uid;
        this.courseCatalog = courseCatalog;
    }
}
