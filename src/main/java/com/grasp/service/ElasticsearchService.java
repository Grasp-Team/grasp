package com.grasp.service;

import com.grasp.exception.ServiceException;
import com.grasp.model.User;
import com.grasp.model.dto.UserDTO;
import com.grasp.model.util.EntityConverter;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ElasticsearchService {

    private String index = System.getenv("SEARCHBOX_INDEX");
    private JestClient client;
    private EntityConverter entityConverter;

    private final static String TUTOR_TYPE = "tutor";

    @Autowired
    public ElasticsearchService(JestClientFactory clientFactory, EntityConverter entityConverter) {
        this.client = clientFactory.getObject();
        this.entityConverter = entityConverter;
    }

    public void upsertTutor(User user) {
        try {
            client.execute(new Index.Builder(entityConverter.convertToDTO(user)).index(index).type(TUTOR_TYPE).build());
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: Unable to build index");
        }
    }

    public void deleteTutor(UUID tutorId) {
        try {
            client.execute(new Delete.Builder(tutorId.toString()).index(index).type(TUTOR_TYPE).build());
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: Unable to delete tutor + " + tutorId);
        }
    }

}
