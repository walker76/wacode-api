package api.domain.company;

import api.domain.order.OrderRequest;
import api.domain.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@Data
@AllArgsConstructor
public class Company {
    long id;
    String phoneNumber;
    String address;

    public Company(CompanyRequest request){
        this.id = generateUniqueId();
        this.phoneNumber = request.getPhoneNumber();
        this.address = request.getAddress();

    }

    private long generateUniqueId() {
        int val = -1;

        do {
            val = new Random().nextInt(200000);
        } while (val < 0);

        return (long) val;
    }
}
