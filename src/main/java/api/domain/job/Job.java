package api.domain.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    String id;

    String title;
    JobType type;
    String description;
    long lat;
    long lang;
    JobStatus status;

    String posterId;
    String workerId;

    public Job(JobRequest request){
        this.id = generateUniqueId();
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.lang = request.getLang();
        this.lat = request.getLat();
        this.type = request.getType();
        this.posterId = request.getEmail();
        this.status = JobStatus.PENDING;

    }

    public Job(String id, String title, JobType type, String description, long lat, long lang, JobStatus status, String posterId) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.lat = lat;
        this.lang = lang;
        this.status = status;
        this.posterId = posterId;
    }

    public Job(String title, JobType type, String description, long lat, long lang, JobStatus status, String posterId) {
        this.id = generateUniqueId();
        this.title = title;
        this.type = type;
        this.description = description;
        this.lat = lat;
        this.lang = lang;
        this.status = status;
        this.posterId = posterId;
    }

    private String generateUniqueId() {
        int val = -1;

        do {
            val = new Random().nextInt(200000);
        } while (val < 0);

        return new Integer(val).toString();
    }
}
