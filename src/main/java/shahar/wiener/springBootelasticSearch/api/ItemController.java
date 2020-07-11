package shahar.wiener.springBootelasticSearch.api;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shahar.wiener.springBootelasticSearch.service.ItemService;
import shahar.wiener.springBootelasticSearch.model.Item;

@Data
@RestController
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService=itemService;
    }

    @GetMapping
    public ResponseEntity alive() {
        return ResponseEntity.ok("Item ElasticSearch application is alive");
    }

    @PostMapping("/items")
    public ResponseEntity createItem(@RequestBody Item item){
        return ResponseEntity.ok(this.itemService.createItem(item));
    }

    @GetMapping("/items")
    public ResponseEntity getItemsByName(@RequestParam String name){
        return ResponseEntity.ok(this.itemService.getItemByName(name));
    }

    @GetMapping("/items/tags")
    public ResponseEntity getItemsByTags(@RequestParam String tags, Pageable pageable){
        return ResponseEntity.ok(this.itemService.getItemByTags(tags, pageable));
    }

    @GetMapping("/items/tags/title")
    public ResponseEntity getItemsByTagsAndTitle(@RequestParam String tags, Pageable pageable){
        return ResponseEntity.ok(this.itemService.getItemByTagsAndTitle(tags, pageable));
    }

}
