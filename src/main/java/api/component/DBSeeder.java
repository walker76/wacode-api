package api.component;

import api.domain.job.Job;
import api.domain.job.JobStatus;
import api.domain.job.JobType;
import api.repository.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;


@Component
public class DBSeeder implements CommandLineRunner {

    private JobRepository jobRepository;

    public DBSeeder(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void run(String... args) throws Exception {
/*
        jobRepository.deleteAll();
        String userId = generateUniqueId().toString();

        // Implement adding default users

        jobRepository.insert(new Job(
                generateUniqueId().toString(),
                "Job 1",
                JobType.FOOD,
                "Test Food Job",
                0,
                0,
                JobStatus.ACCEPTED,
                userId
        ));

        jobRepository.insert(new Job(
                generateUniqueId().toString(),
                "Job 2",
                JobType.FOOD,
                "Test Food Job",
                0,
                0,
                JobStatus.ACCEPTED,
                userId
        ));

        jobRepository.insert(new Job(
                generateUniqueId().toString(),
                "Job 3",
                JobType.RECREATION,
                "Test Recreation Job",
                0,
                0,
                JobStatus.PENDING,
                userId

        ));*/
    }

    private Integer generateUniqueId() {
        int val = -1;

        do {
            val = new Random().nextInt(200000);
        } while (val < 0);

        return val;
    }

}
