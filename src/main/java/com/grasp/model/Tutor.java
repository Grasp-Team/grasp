package com.grasp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name="tutors", schema="users")
public class Tutor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long tutorId;
    @JoinColumn(name = "uid")
    @OneToOne
    @JsonIgnore
    private User uid;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseCatalog courseCatalog;

    public Tutor() {
    }

    public Tutor(User uid, CourseCatalog courseCatalog) {
        this.uid = uid;
        this.courseCatalog = courseCatalog;
    }

}
