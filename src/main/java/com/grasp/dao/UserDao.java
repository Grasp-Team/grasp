package com.grasp.dao;

import com.grasp.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
public interface UserDao extends CrudRepository<User, UUID> {
    User findUserById(UUID id);
    User findUserByFirstName(String name);
}
