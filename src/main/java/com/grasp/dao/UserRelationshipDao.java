package com.grasp.dao;

import com.grasp.model.entity.UserRelationship;
import org.springframework.data.repository.CrudRepository;

public interface UserRelationshipDao extends CrudRepository<UserRelationship,Long> {
    UserRelationship findUserRelationshipById(Long id);
}
