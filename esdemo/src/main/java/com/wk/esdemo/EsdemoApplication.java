package com.wk.esdemo;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.node.NodeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@SpringBootApplication
@RestController
public class EsdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsdemoApplication.class, args);
    }

    @Autowired
    private ElasticsearchTemplate template;

    @GetMapping("/")
    public List<TestIndex> nodes(){
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery().must(termQuery("description", "des-2673884036341109922"))
                ).build();
        List<TestIndex> rs = template.queryForList(query, TestIndex.class);
        System.out.println(rs.size());
        return rs;
    }
}
