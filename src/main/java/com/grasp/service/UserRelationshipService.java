package com.grasp.service;

import com.grasp.dao.UserRelationshipDao;
import com.grasp.model.UserRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRelationshipService {

    private UserRelationshipDao userRelationshipDao;

    @Autowired
    public UserRelationshipService(UserRelationshipDao userRelationshipDao) {
        this.userRelationshipDao = userRelationshipDao;
    }

    public UserRelationship addNewRelationship(UserRelationship newRelationship) {
        return userRelationshipDao.save(newRelationship);
    }

    public UserRelationship updateExistingRelationship(Long id, UserRelationship.Status status) {

        UserRelationship existingRelationship = userRelationshipDao
                .findUserRelationshipById(id);

        existingRelationship.setRelationshipStatus(status);

        return userRelationshipDao.save(existingRelationship);

    }

    public UserRelationship getRelationship(UserRelationship userRelationship) {
        return userRelationshipDao
                .findUserRelationshipById(userRelationship.getId());
    }


}
