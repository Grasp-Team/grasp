package com.grasp.dao;

import com.grasp.model.entity.UserRelationship;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserRelationshipDao extends CrudRepository<UserRelationship,Long> {
    UserRelationship findUserRelationshipById(Long id);
    List<UserRelationship> findUserRelationshipsByTutorId(UUID tutorId);
    List<UserRelationship> findUserRelationshipsByUserId(UUID userId);
}
