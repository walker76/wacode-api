package api.domain.job;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobUpdateRequest {
    String id;
    JobStatus status;
    String workerId;
}
