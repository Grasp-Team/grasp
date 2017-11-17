package com.grasp.controller;

import com.grasp.dao.UserDao;
import com.grasp.model.dto.UserListDTO;
import com.grasp.model.util.EntityConverter;
import com.grasp.service.TutorService;
import com.grasp.util.TutorTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static com.grasp.util.TutorTestUtil.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TutorControllerTest {

    private TutorService tutorService = mock(TutorService.class);
    private UserDao userDao = mock(UserDao.class);
    private TutorController tutorController;

    @Before
    public void setUp() throws Exception {
        tutorController = null;//new TutorController(tutorService, new EntityConverter(new ModelMapper(), userDao));
    }

    @Test
    public void testGetAllTutors() throws Exception {

        when(tutorService.getAllTutors()).thenReturn(TutorTestUtil.TUTOR_LIST);
        when(userDao.findUserById(TUTOR_1.getId())).thenReturn(TUTOR_1);
        when(userDao.findUserById(TUTOR_2.getId())).thenReturn(TUTOR_2);
        when(userDao.findUserById(TUTOR_3.getId())).thenReturn(TUTOR_3);
        when(userDao.findUserById(TUTOR_4.getId())).thenReturn(TUTOR_4);

        ResponseEntity<UserListDTO> courses = tutorController.getAllTutors();

        assertEquals(HttpStatus.OK, courses.getStatusCode());
        assertEquals(TUTOR_LIST.size(), courses.getBody().getUsers().size());
    }

    @Test
    public void testGetAllTutorsNullReturn() throws Exception {
        when(tutorService.getAllTutors()).thenReturn(null);

        ResponseEntity<UserListDTO> courses = tutorController.getAllTutors();

        assertEquals(HttpStatus.OK, courses.getStatusCode());
        assertEquals(0, courses.getBody().getUsers().size());
    }

    @Test
    public void testGetAllTutorsEmptyList() throws Exception {
        when(tutorService.getAllTutors()).thenReturn(new ArrayList<>());

        ResponseEntity<UserListDTO> courses = tutorController.getAllTutors();

        assertEquals(HttpStatus.OK, courses.getStatusCode());
        assertEquals(0, courses.getBody().getUsers().size());
    }
}