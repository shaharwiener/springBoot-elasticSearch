package shahar.wiener.springBootelasticSearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Document(indexName = "item", createIndex = true)
public class Item {
    @Id
    private String id;
    private String name;
    @Field(pattern = "+")
    private String title;
    @Field(type = FieldType.Keyword)
    private List<String> tags;
}
