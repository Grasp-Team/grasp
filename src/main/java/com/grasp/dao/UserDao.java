package com.grasp.dao;

import com.grasp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
public interface UserDao extends CrudRepository<User, UUID> {
    User findUserById(UUID id);
    User findUserByFirstName(String name);
    User findUserByEmail(String name);
    List<User> findAllByUserType(User.UserType userType);
}
