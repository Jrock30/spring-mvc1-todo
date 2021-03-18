package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data 얘는 위험하다. 모든 것을 다 만들어줌.(예측하지 못하게 동작할 수 있으므로 필요한 것만 사용하도록)
@Setter
@Getter
public class Item {

    private Long id;
    private String itemName;
    // 가격이 없을 수도 있으므로 0이 아닌 NULL이 들어갈 수 있으므로 래퍼 타입을 사용한다.(primitive X, 상황에 맞게 사용)
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
