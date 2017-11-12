package com.grasp.model.dto;

import com.grasp.model.UserRelationship;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Getter
@Setter
public class UserRelationshipDTO {
    private UUID tutorId;
    private UUID userId;
    private UserRelationship.Status relationshipStatus;

    public UserRelationshipDTO() {
    }

    public UserRelationshipDTO(UUID tutorId, UUID userId) {
        this.tutorId = tutorId;
        this.userId = userId;
        this.relationshipStatus = UserRelationship.Status.PENDING;
    }

    public UserRelationshipDTO(UUID tutorId, UUID userId, UserRelationship.Status relationshipStatus) {
        this.tutorId = tutorId;
        this.userId = userId;
        this.relationshipStatus = relationshipStatus;
    }
}
