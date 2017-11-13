package com.grasp.dao;

import com.grasp.model.entity.Subject;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface SubjectDao extends CrudRepository<Subject,String> {

}
