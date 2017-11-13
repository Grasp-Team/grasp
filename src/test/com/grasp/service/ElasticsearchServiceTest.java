package com.grasp.service;

import com.grasp.model.util.EntityConverter;
import io.searchbox.client.JestClientFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ElasticsearchServiceTest {


    @Test
    public void testValue() throws Exception {

        List<String> list = new ArrayList<>();
        list.add("MATH");
        list.add("CS");
        list.add("PHYS");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder qb = boolQuery();

        for(String string : list) {
            qb.should(matchQuery("tutors.courseCatalog.subject", string));
        }

        sourceBuilder.query(qb);

        System.out.println(sourceBuilder.toString());
    }
}