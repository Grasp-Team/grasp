package com.grasp.dao;

import com.grasp.model.Tutor;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface TutorDao extends CrudRepository<Tutor, Long> {
}
