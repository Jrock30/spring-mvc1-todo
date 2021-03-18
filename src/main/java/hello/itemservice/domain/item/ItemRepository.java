package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {

    // 실제는 HashMap 쓰면 안 됨.(ConcurrentHashMap 사용)
    private static final Map<Long, Item> store = new HashMap<>();
    // 이 또한 롱이 아닌 automic
    private static long sequence = 0L; //스프링은 싱글톤 이기에 따로 static을 쓰진 않아도 됨.

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        // 감싸서 반환하면 변경이 안되니 이게 낫다.
        return new ArrayList<>(store.values());
    }

    // 간단하게
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        // 아래와 같은 경우는 ID를 따로 사용하지 않기 때문에 DTO를 따로 만드는 것이 낫다.
        // 명확하게 필요 없는 필드는 제거하고 따로 만드는 것이 낫다.(설계상 명확한 것이 낫다., 중복보다는 명확성)
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    // clear
    public void clearStore() {
        store.clear();
    }
}
