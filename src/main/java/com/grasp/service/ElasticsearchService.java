package com.grasp.service;

import com.grasp.exception.ServiceException;
import com.grasp.model.entity.User;
import com.grasp.model.entity.UserSubject;
import com.grasp.model.dto.UserDTO;
import com.grasp.model.dto.UserListDTO;
import com.grasp.model.util.EntityConverter;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.core.*;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@Service
public class ElasticsearchService {

    private static final int SEARCH_LIMIT = 50;
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

        for (UserSubject subject : subjects) {
            qb.should(matchQuery("tutors.courseCatalog.subject", subject.getSubject()));
        }

        sourceBuilder.query(qb).size(SEARCH_LIMIT).from(0);

        return executeQuery(sourceBuilder);
    }

    public UserListDTO searchTutors(String queryString) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        System.out.println("QUERY STRING: " + queryString);

        MultiMatchQueryBuilder multiMatchQueryBuilder = multiMatchQuery(queryString, "tutors.courseCatalog.description",
                "tutors.courseCatalog.courseName", "tutors.courseCatalog.code", "tutors.courseCatalog.subject");

        sourceBuilder.query(multiMatchQueryBuilder).size(SEARCH_LIMIT).from(0);

        return executeQuery(sourceBuilder);
    }

    private UserListDTO executeQuery(SearchSourceBuilder sourceBuilder) {

        String query = sourceBuilder.toString();

        System.out.println("Executing Query: " + query);

        Search search = new Search.Builder(query)
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
