package shahar.wiener.springBootelasticSearch.service;

import lombok.Data;
import lombok.SneakyThrows;
import org.elasticsearch.search.SearchHit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import shahar.wiener.springBootelasticSearch.repository.ItemCustomRepository;
import shahar.wiener.springBootelasticSearch.repository.ItemRepository;
import shahar.wiener.springBootelasticSearch.model.Item;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class ItemService {
    private ItemRepository itemRepository;
    private ItemCustomRepository itemCustomRepository;

    public ItemService(ItemRepository itemRepository, ItemCustomRepository itemCustomRepository){
        this.itemRepository=itemRepository;
        this.itemCustomRepository=itemCustomRepository;
    }

    public Item createItem(Item item) {
        return this.itemRepository.save(item);
    }

    @SneakyThrows
    public SearchHits<Item> getItemByTags(String question, Pageable pageable)  {
        List<String> tags = Arrays.stream(question.split("\\+")).collect(Collectors.toList());
        return this.itemCustomRepository.getItemsByTags(tags, pageable);
    }

    @SneakyThrows
    public SearchHits<Item> getItemByTagsAndTitle(String question, Pageable pageable)  {
        List<String> tags = Arrays.stream(question.split("\\+")).collect(Collectors.toList());
        return this.itemCustomRepository.getItemsByTagsAndTitle(tags, question, pageable);
    }

    public List<Item> getItemByName(String name){
        return this.itemRepository.getItemByName(name);
    }
}
