package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    // 테스트가 끝난 후 값을 초기화 하기 위해 사용하자.
    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() throws Exception {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item saveItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);

    }

    @Test
    void findAll() throws Exception {
        //given
        Item item = new Item("itemA", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item);
        itemRepository.save(item2);

        //when
        List<Item> findAll = itemRepository.findAll();

        //then
        assertThat(findAll.size()).isEqualTo(2);
        assertThat(findAll).contains(item, item2);
    }

    @Test

    void updateItem() throws Exception {
        //given
        Item item = new Item("itemA", 10000, 10);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updatePram = new Item("itemA", 20000, 30);
        itemRepository.update(itemId, updatePram);

        //then
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updatePram.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updatePram.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updatePram.getQuantity());
    }

}