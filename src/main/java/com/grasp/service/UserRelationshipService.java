package com.grasp.service;

import com.grasp.dao.UserRelationshipDao;
import com.grasp.exception.ServiceException;
import com.grasp.model.entity.UserRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public UserRelationship getRelationshipById(Long id) {

        if (id == null) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Id is null.");
        }

        return userRelationshipDao.findUserRelationshipById(id);
    }
    
    public List<UserRelationship> getRelationshipByTutor(UUID tutorid) {

        if (tutorid == null) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "TutorId is null.");
        }
        
        return userRelationshipDao.findUserRelationshipsByTutorId(tutorid);
    }

    public List<UserRelationship> getRelationshipByUser(UUID userId) {

        if (userId == null) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "UserId is null.");
        }

        return userRelationshipDao.findUserRelationshipsByUserId(userId);
    }


}
