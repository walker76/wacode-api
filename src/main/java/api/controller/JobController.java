package api.controller;

import api.domain.job.*;
import api.domain.user.User;
import api.repository.JobRepository;
import api.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job")
public class JobController {

    private JobRepository jobRepository;
    private UserRepository userRepository;

    public JobController(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<Job> getAll(){
        return this.jobRepository.findAll();
    }

    @GetMapping("/findById/{id}")
    public Job findById(@PathVariable("id") String id){
        Optional<Job> jobOptional = this.jobRepository.findById(id);
        return jobOptional.isPresent() ? jobOptional.get() : null;
    }

    @GetMapping("/findByJobType/{jobType}")
    public List<Job> getByJobType(@PathVariable("jobType") String jobType){
        return this.jobRepository.findAllByTypeEquals(JobType.valueOf(jobType));
    }

    @GetMapping("/findByJobStatus/{jobStatus}")
    public List<Job> getByJobType(@PathVariable("jobStatus") JobStatus jobStatus){
        return this.jobRepository.findAllByStatusIs(jobStatus);
    }

    @PutMapping("/updateJobStatus/")
    public void getByJobType(@RequestBody JobUpdateRequest jobUpdateRequest){
        System.out.println(jobUpdateRequest);
        Optional<Job> optionalJob = this.jobRepository.findById(jobUpdateRequest.getId());
        if(optionalJob.isPresent()){
            Job job = optionalJob.get();
            job.setStatus(jobUpdateRequest.getStatus());
            if(jobUpdateRequest.getStatus().equals(JobStatus.ACCEPTED)){
                Optional<User> optionalUser = userRepository.findById(jobUpdateRequest.getWorkerId());
                if(optionalUser.isPresent()){
                    User user = optionalUser.get();
                    user.getJobsWorking().add(jobUpdateRequest.getId());
                    userRepository.save(user);
                }
            }
            job.setWorkerId(jobUpdateRequest.getWorkerId());
            this.jobRepository.save(job);
        }
    }

    @PutMapping("/insert")
    public void insert(@RequestBody JobRequest jobRequest){
        Job newJob = new Job(jobRequest);
        User user = userRepository.findById(newJob.getPosterId()).get();
        user.getJobsPosted().add(newJob.getId());
        userRepository.save(user);
        this.jobRepository.insert(newJob);
    }

    @PostMapping("/update")
    public void update(@RequestBody Job job){
        this.jobRepository.save(job);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id){
        this.jobRepository.deleteById(id);
    }

}
