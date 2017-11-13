package com.grasp.controller;

import com.grasp.exception.ControllerException;
import com.grasp.model.CourseCatalog;
import com.grasp.model.dto.CourseCatalogListDTO;
import com.grasp.service.CourseCatalogService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static com.grasp.util.CourseCatalogTestUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class CourseCatalogControllerTest {

    private CourseCatalogService courseCatalogService = mock(CourseCatalogService.class);
    private CourseCatalogController courseCatalogController;

    @Before
    public void setUp() throws Exception {
        courseCatalogController = new CourseCatalogController(courseCatalogService);
    }

    @Test
    public void testGetAllCourses() throws Exception {

        when(courseCatalogService.getAllCourses()).thenReturn(COURSE_CATALOG_LIST);

        ResponseEntity<CourseCatalogListDTO> courses = courseCatalogController.getAllCourses();

        assertEquals(HttpStatus.OK, courses.getStatusCode());
        assertEquals(COURSE_CATALOG_LIST.size(), courses.getBody().getCourseCatalogs().size());
    }

    @Test
    public void testGetAllCoursesNullReturn() throws Exception {
        when(courseCatalogService.getAllCourses()).thenReturn(null);

        ResponseEntity<CourseCatalogListDTO> courses = courseCatalogController.getAllCourses();

        assertEquals(HttpStatus.OK, courses.getStatusCode());
        assertEquals(0, courses.getBody().getCourseCatalogs().size());
    }

    @Test
    public void testGetAllCoursesEmptyList() throws Exception {
        when(courseCatalogService.getAllCourses()).thenReturn(new ArrayList<>());

        ResponseEntity<CourseCatalogListDTO> courses = courseCatalogController.getAllCourses();

        assertEquals(HttpStatus.OK, courses.getStatusCode());
        assertEquals(0, courses.getBody().getCourseCatalogs().size());
    }

    @Test
    public void testGetCourseByCode() throws Exception {
        when(courseCatalogService.getCourseByCode(Mockito.anyString())).thenReturn(COURSE_1);

        ResponseEntity<CourseCatalog> courses = courseCatalogController.getCourseByCode("CS123");

        assertEquals(HttpStatus.OK, courses.getStatusCode());
        assertEquals("CS123", courses.getBody().getCode());
    }

    @Test
    public void testGetCourseByCodeNull() throws Exception {

        try {
            courseCatalogController.getCourseByCode(null);
            fail();
        } catch (ControllerException e) {
            assertEquals("ERROR: Code cannot be null", e.getMessage());
            assertEquals(HttpStatus.NOT_FOUND, e.getHttpStatus());
        }
    }

    @Test
    public void testGetCourseByCodeNonExistent() throws Exception {
        String code = "ABC123";

        when(courseCatalogService.getCourseByCode(Mockito.anyString())).thenReturn(null);

        try {
            courseCatalogController.getCourseByCode(code);
            fail();
        } catch (ControllerException e) {
            assertEquals("ERROR: Cannot find: " + code, e.getMessage());
            assertEquals(HttpStatus.NOT_FOUND, e.getHttpStatus());
        }
    }

    @Test
    public void testGetCourseBySubject() throws Exception {
        when(courseCatalogService.getCoursesBySubject(Mockito.anyString())).thenReturn(COURSE_CATALOG_LIST_CS);

        ResponseEntity<CourseCatalogListDTO> courses = courseCatalogController.getCoursesBySubject("CS");

        assertEquals(HttpStatus.OK, courses.getStatusCode());
        assertEquals(COURSE_CATALOG_LIST_CS.size(), courses.getBody().getCourseCatalogs().size());
        assertEquals("CS123", courses.getBody().getCourseCatalogs().get(0).getCode());
        assertEquals("CS245", courses.getBody().getCourseCatalogs().get(1).getCode());
    }

    @Test
    public void testGetCourseBySubjectNull() throws Exception {

        try {
            courseCatalogController.getCoursesBySubject(null);
            fail();
        } catch (ControllerException e) {
            assertEquals("ERROR: Subject cannot be null", e.getMessage());
            assertEquals(HttpStatus.NOT_FOUND, e.getHttpStatus());
        }
    }

    @Test
    public void testGetCourseBySubjectNonExistent() throws Exception {
        String subject = "CS";

        when(courseCatalogService.getCoursesBySubject(Mockito.anyString())).thenReturn(new ArrayList<>());

        try {
            courseCatalogController.getCoursesBySubject(subject);
            fail();
        } catch (ControllerException e) {
            assertEquals("ERROR: Did not find courses for subject: " + subject, e.getMessage());
            assertEquals(HttpStatus.NOT_FOUND, e.getHttpStatus());
        }
    }
}