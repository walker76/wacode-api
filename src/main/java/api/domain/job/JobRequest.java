package api.domain.job;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class JobRequest {
    String title;
    JobType type;
    String description;
    Long lat, lang;
    String email;
}
