package com.grasp.service;

import com.grasp.dao.UserRelationshipDao;
import com.grasp.exception.ControllerException;
import com.grasp.exception.ServiceException;
import com.grasp.model.entity.UserRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.grasp.model.entity.UserRelationship.*;

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

    public UserRelationship updateExistingRelationship(Long id, Status status) {

        UserRelationship existingRelationship = userRelationshipDao
                .findUserRelationshipById(id);

        existingRelationship.setRelationshipStatus(status);

        return userRelationshipDao.save(existingRelationship);

    }

    public void deleteRelationship(Long id) {
        try {
            userRelationshipDao.delete(id);
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "ERROR: Failed to delete relationship with id: " + id);
        }
    }

    public UserRelationship getRelationshipById(Long id) {

        if (id == null) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Id is null.");
        }

        return userRelationshipDao.findUserRelationshipById(id);
    }

    public List<UserRelationship> getRelationshipByTutor(UUID tutorId, String status) {

        if (tutorId == null) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "TutorId is null.");
        }

        //TODO: get a better way to convert this
        if (status == null || status.isEmpty()) {
            return userRelationshipDao.findUserRelationshipsByTutorId(tutorId);
        } else if (status.equals("ACCEPTED")) {
            return userRelationshipDao.findUserRelationshipsByTutorIdAndRelationshipStatus(tutorId, Status.ACCEPTED);
        } else if (status.equals("PENDING")) {
            return userRelationshipDao.findUserRelationshipsByTutorIdAndRelationshipStatus(tutorId, Status.PENDING);
        } else if (status.equals("REJECTED")) {
            return userRelationshipDao.findUserRelationshipsByTutorIdAndRelationshipStatus(tutorId, Status.REJECTED);
        } else {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid status: " + status + " supplied.");
        }
    }

    public List<UserRelationship> getRelationshipByUser(UUID userId, String status) {

        if (userId == null) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "UserId is null.");
        }

        if (status == null || status.isEmpty()) {
            return userRelationshipDao.findUserRelationshipsByUserId(userId);
        } else if (status.equals("ACCEPTED")) {
            return userRelationshipDao.findUserRelationshipsByUserIdAndRelationshipStatus(userId, Status.ACCEPTED);
        } else if (status.equals("PENDING")) {
            return userRelationshipDao.findUserRelationshipsByUserIdAndRelationshipStatus(userId, Status.PENDING);
        } else if (status.equals("REJECTED")) {
            return userRelationshipDao.findUserRelationshipsByUserIdAndRelationshipStatus(userId, Status.REJECTED);
        } else {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid status: " + status + " supplied.");
        }
    }


    public UserRelationship getRelationshipByUserAndTutor(UUID user, UUID tutor) {

        if (tutor == null) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: no provided tutor.");
        } else if (user == null) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: no provided user.");
        }

        return userRelationshipDao.findUserRelationshipByUserIdAndTutorId(user, tutor);
    }
}
