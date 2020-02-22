package api.controller;

import api.domain.job.Job;
import api.domain.user.User;
import api.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<User> getAll(){
        return this.userRepository.findAll();
    }

    @GetMapping("/findById/{id:.+}")
    public User findById(@PathVariable("id") String id){
        Optional<User> userOptional = this.userRepository.findById(id);
        return userOptional.isPresent() ? userOptional.get() : null;
    }

    @GetMapping("/findJobsById/{id:.+}")
    public List<String> findJobsById(@PathVariable("id") String id){
        Optional<User> userOptional = this.userRepository.findById(id);
        List<String> ret = new ArrayList<>();

        if(userOptional.isPresent()){
            User user = userOptional.get();
            List<String> posted = user.getJobsPosted();
            List<String> working = user.getJobsWorking();
            List<String> combined = new ArrayList<>();
            combined.addAll(working);
            combined.addAll(posted);
            return combined;
        }
        return ret;
    }

    @GetMapping("/findWorkingJobsById/{id:.+}")
    public List<String> findWorkingJobsById(@PathVariable("id") String id){
        Optional<User> userOptional = this.userRepository.findById(id);
        List<String> ret = new ArrayList<>();

        if(userOptional.isPresent()){
            User user = userOptional.get();
            return user.getJobsWorking();
        }
        return ret;
    }

    @GetMapping("/findPostingJobsById/{id:.+}")
    public List<String> findPostingJobsById(@PathVariable("id") String id){
        Optional<User> userOptional = this.userRepository.findById(id);
        List<String> ret = new ArrayList<>();

        if(userOptional.isPresent()){
            User user = userOptional.get();
            return user.getJobsPosted();
        }
        return ret;
    }

    @PutMapping("/insert")
    public void insert(@RequestBody User user){
        System.out.println(user);
        this.userRepository.insert(user);
    }

    @PostMapping("/update")
    public void update(@RequestBody User user){
        this.userRepository.save(user);
    }

    @DeleteMapping("/delete/{id:.+}")
    public void delete(@PathVariable("id") String id){
        this.userRepository.deleteById(id);
    }
}
