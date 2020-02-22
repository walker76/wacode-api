package api.component;

import api.domain.order.JobStatus;
import api.domain.order.JobType;
import api.repository.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        jobRepository.insert(new Order(
                generateUniqueId().toString(),
                "Order 1",
                JobType.FOOD,
                "Test Food Order",
                0,
                0,
                OrderStatus.ACCEPTED,
                userId
        ));

        jobRepository.insert(new Order(
                generateUniqueId().toString(),
                "Order 2",
                JobType.FOOD,
                "Test Food Order",
                0,
                0,
                OrderStatus.ACCEPTED,
                userId
        ));

        jobRepository.insert(new Order(
                generateUniqueId().toString(),
                "Order 3",
                JobType.RECREATION,
                "Test Recreation Order",
                0,
                0,
                OrderStatus.PENDING,
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
