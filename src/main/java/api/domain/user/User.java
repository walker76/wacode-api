package api.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    String email;
    List<Long> jobsPosted;
    List<Long> jobsWorking;

    User(String email){
        this.email = email;
        this.jobsPosted = new ArrayList<>();
        this.jobsWorking = new ArrayList<>();
    }
}
