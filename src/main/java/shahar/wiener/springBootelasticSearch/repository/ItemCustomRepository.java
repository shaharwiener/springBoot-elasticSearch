package shahar.wiener.springBootelasticSearch.repository;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;
import shahar.wiener.springBootelasticSearch.model.Item;

import java.io.IOException;
import java.util.List;

@Repository
public class ItemCustomRepository {

    RestHighLevelClient restHighLevelClient;

    ElasticsearchOperations elasticsearchOperations;
    public ItemCustomRepository(ElasticsearchOperations elasticsearchOperations){
        this.elasticsearchOperations=elasticsearchOperations;
    }

    public SearchHits<Item>  getItemsByTags(List<String> tags, Pageable pageable) throws IOException {

        final BoolQueryBuilder qb = QueryBuilders.boolQuery();
        tags.stream().forEach(tag -> {
            qb.should(QueryBuilders.termQuery("tags",tag));

        });

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(qb);
        nativeSearchQueryBuilder.withPageable(pageable);
        SearchHits<Item> response =  elasticsearchOperations.search(nativeSearchQueryBuilder.build(), Item.class);
//        SearchPage<Item> page = SearchHitSupport.searchPageFor(response, nativeSearchQueryBuilder.p)

        return response;
    }


    public SearchHits<Item>  getItemsByTagsAndTitle(List<String> tags, String title, Pageable pageable) throws IOException {

        final BoolQueryBuilder qb = QueryBuilders.boolQuery();
        tags.stream().forEach(tag -> {
            qb.should(QueryBuilders.termQuery("tags",tag));

        });

        qb.should(QueryBuilders.matchQuery("title", title));

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(qb);
        nativeSearchQueryBuilder.withPageable(pageable);
        SearchHits<Item> response =  elasticsearchOperations.search(nativeSearchQueryBuilder.build(), Item.class);

        return response;
    }
}
