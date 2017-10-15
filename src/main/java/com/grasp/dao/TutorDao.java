package com.grasp.dao;

import com.grasp.model.Tutor;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TutorDao extends CrudRepository<Tutor, Long> {
}
