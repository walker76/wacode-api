package api.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderUpdateRequest {
    long id;
    OrderStatus status;
    long workerId;
}
