package com.grasp.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "firstName", nullable = false)
    private String lastName;
    @Column(name = "firstName", nullable = false)
    private String email;
    @Column(name = "firstName", nullable = false)
    private int year;
    @Column(name = "firstName", nullable = false)
    private String program;
    @Column(name = "firstName", nullable = false)
    private String faculty;

}
