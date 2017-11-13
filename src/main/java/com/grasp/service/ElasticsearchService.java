package com.grasp.service;

import com.grasp.exception.ServiceException;
import com.grasp.model.User;
import com.grasp.model.UserSubject;
import com.grasp.model.dto.UserDTO;
import com.grasp.model.dto.UserListDTO;
import com.grasp.model.util.EntityConverter;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

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

    public UserListDTO searchTutorBySubject(List<UserSubject> subjects) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder qb = boolQuery();

        for(UserSubject subject : subjects) {
            qb.should(matchQuery("tutors.courseCatalog.subject", subject.getSubject()));
        }

        sourceBuilder.query(qb);

        Search search = new Search.Builder(sourceBuilder.toString())
                .addIndex(index)
                .addType(TUTOR_TYPE)
                .build();

        SearchResult result;

        try {
            result = client.execute(search);
        } catch (IOException e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: unable to search for tutors");
        }

        return new UserListDTO(result.getSourceAsObjectList(UserDTO.class));
    }
}
