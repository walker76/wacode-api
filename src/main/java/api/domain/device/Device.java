package api.domain.device;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Random;

@Data
@AllArgsConstructor
public class Device {
    Long deviceId;
    ArrayList<Long> jobsPosted;

    public Device(){
        this.deviceId = generateUniqueId();
        jobsPosted = new ArrayList<>();
    }

    private long generateUniqueId() {
        int val = -1;

        do {
            val = new Random().nextInt(200000);
        } while (val < 0);

        return (long) val;
    }
}
