package com.grasp.dao;

import com.grasp.model.UserSubject;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserSubjectDao extends CrudRepository<UserSubject,Long> {
}
