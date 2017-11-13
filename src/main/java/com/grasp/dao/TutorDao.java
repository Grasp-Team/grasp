package com.grasp.dao;

import com.grasp.model.Tutor;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
public interface TutorDao extends CrudRepository<Tutor, Long> {
    List<Tutor> findTutorsByCourseCatalog_Code(String courseCode);
    void deleteAllByUid(UUID tutorId);
}
