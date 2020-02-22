package api.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
public class User {
    @Id
    Long id;
    String email;
    List<Long> jobsWorking;

    public User(){
        this.id = generateUniqueId();
        this.email = null;
        this.jobsWorking = new ArrayList<>();
    }

    public User(String email){
        this.id = generateUniqueId();
        this.email = email;
        this.jobsWorking = new ArrayList<>();
    }

    public User(UserRequest request){
        this.id = generateUniqueId();
        this.email = request.getEmail();
        this.jobsWorking = new ArrayList<>();

    }

    private long generateUniqueId() {
        int val = -1;

        do {
            val = new Random().nextInt(200000);
        } while (val < 0);

        return (long) val;
    }
}
