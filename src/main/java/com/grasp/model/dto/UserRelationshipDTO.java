package com.grasp.model.dto;

import com.grasp.model.entity.UserRelationship;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserRelationshipDTO {
    private Long id;
    private UUID tutorId;
    private UUID userId;
    private UserRelationship.Status relationshipStatus;

    public UserRelationshipDTO() {
    }

    public UserRelationshipDTO(Long id, UUID tutorId, UUID userId) {
        this.id = id;
        this.tutorId = tutorId;
        this.userId = userId;
        this.relationshipStatus = UserRelationship.Status.PENDING;
    }

    public UserRelationshipDTO(Long id, UUID tutorId, UUID userId, UserRelationship.Status relationshipStatus) {
        this.id = id;
        this.tutorId = tutorId;
        this.userId = userId;
        this.relationshipStatus = relationshipStatus;
    }
}
