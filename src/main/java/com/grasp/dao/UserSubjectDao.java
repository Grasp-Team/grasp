package com.grasp.dao;

import com.grasp.model.UserSubject;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
public interface UserSubjectDao extends CrudRepository<UserSubject,Long> {
    List<UserSubject> findAllByUserId(UUID userId);
    void deleteAllByUserId(UUID userId);
}
