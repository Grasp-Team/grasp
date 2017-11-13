package com.grasp.service;

import com.grasp.exception.ServiceException;
import com.grasp.model.User;
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

    @Value("searchbox.index")
    private String index;
    private JestClient client;

    private final static String TUTOR_TYPE = "tutor";

    @Autowired
    public ElasticsearchService(JestClientFactory clientFactory) {
        this.client = clientFactory.getObject();
    }

    public void upsertTutor(User user) {
        // TODO: properly map these to correct object

        try {
//            JestResult result = client.execute(new Get.Builder(index, user.getId().toString()).type(TUTOR_TYPE).build());
//
//            if(result.isSucceeded()) {
//                client.execute(new )
//            } else {
//            }
            client.execute(new Index.Builder(user).index(index).type(TUTOR_TYPE).build());
        } catch (Exception e) {
            //TODO: add proper exception handling
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: Unable to build index");
        }
    }

    public void deleteTutor(UUID tutorId) {
        try {
            client.execute(new Delete.Builder(tutorId.toString()).index(index).type(TUTOR_TYPE).build());
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: Unable to delete tutor + " +  tutorId);
        }
    }

}
