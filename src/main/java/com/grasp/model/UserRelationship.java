package com.grasp.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "relationships", schema = "users")
public class UserRelationship {

    public enum Status {
        ACCEPTED, PENDING, REJECTED
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tutor")
    private UUID tutorId;
    @Column(name = "student")
    private UUID userId;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status relationshipStatus;

    public UserRelationship() {
    }

    public UserRelationship(UUID tutorId, UUID userId, Status relationshipStatus) {
        this.tutorId = tutorId;
        this.userId = userId;
        this.relationshipStatus = relationshipStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRelationship)) {
            return false;
        }

        UserRelationship that = (UserRelationship) o;

        if (!getId().equals(that.getId())) {
            return false;
        }
        if (!getTutorId().equals(that.getTutorId())) {
            return false;
        }
        if (!getUserId().equals(that.getUserId())) {
            return false;
        }
        return getRelationshipStatus() == that.getRelationshipStatus();
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getTutorId().hashCode();
        result = 31 * result + getUserId().hashCode();
        result = 31 * result + getRelationshipStatus().hashCode();
        return result;
    }
}
