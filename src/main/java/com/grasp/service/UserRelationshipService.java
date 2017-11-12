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

    public UserRelationship updateExistingRelationship(UserRelationship updatedRelationship) {

        UserRelationship originalRelationship = userRelationshipDao
                .findUserRelationshipByUserIdAndTutorId(updatedRelationship.getUserId(),
                        updatedRelationship.getTutorId());

        if (originalRelationship == null) {
            return null;
        }

        originalRelationship.setRelationshipStatus(updatedRelationship.getRelationshipStatus());

        return userRelationshipDao.save(originalRelationship);

    }

}
