package com.grasp.dao;

import com.grasp.model.entity.CourseCatalog;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CourseCatalogDao extends CrudRepository<CourseCatalog, String> {
    CourseCatalog findByCode(String code);

    List<CourseCatalog> findAllBySubject(String subject);

    List<CourseCatalog> findAllByCodeIn(List<String> codes);
}
