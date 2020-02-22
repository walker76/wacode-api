package api.repository;

import api.domain.job.Job;
import api.domain.job.JobStatus;
import api.domain.job.JobType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {

    List<Job> findAllByTypeEquals(JobType type);
    List<Job> findAllByStatusIs(JobStatus status);
}
