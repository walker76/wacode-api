package api.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    long id;

    String title;
    String description;
    long lat;
    long lang;
    OrderStatus status;

    long posterId;
    long workerId;

    public Order(OrderRequest request){
        this.id = generateUniqueId();
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.lang = request.getLang();
        this.lat = request.getLat();
        this.posterId = request.getDeviceId();
        this.status = OrderStatus.PENDING;

    }

    public Order(long id, String title, String description, long lat, long lang, OrderStatus status, long posterId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lang = lang;
        this.status = status;
        this.posterId = posterId;
    }

    public Order(String title, String description, long lat, long lang, OrderStatus status, long posterId) {
        this.id = generateUniqueId();
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lang = lang;
        this.status = status;
        this.posterId = posterId;
    }

    private long generateUniqueId() {
        int val = -1;

        do {
            val = new Random().nextInt(200000);
        } while (val < 0);

        return (long) val;
    }
}
