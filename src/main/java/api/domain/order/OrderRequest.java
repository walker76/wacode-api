package api.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequest {
    String title;
    String description;
    Long lat, lang;
    long deviceId;
}
