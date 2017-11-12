package com.grasp.dao;

import com.grasp.model.UserRelationship;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRelationshipDao extends CrudRepository<UserRelationship,Long> {
    UserRelationship findUserRelationshipById(Long id);
}
