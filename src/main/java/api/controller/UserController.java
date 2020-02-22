package api.controller;

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
    public User findById(@PathVariable("id") long id){
        Optional<User> userOptional = this.userRepository.findById(id);
        return userOptional.isPresent() ? userOptional.get() : null;
    }

    @GetMapping("/findOrdersById/{id:.+}")
    public List<Long> findJobsById(@PathVariable("id") long id){
        Optional<User> userOptional = this.userRepository.findById(id);
        List<Long> ret = new ArrayList<>();

        if(userOptional.isPresent()){
            User user = userOptional.get();
            List<Long> posted = user.getJobsPosted();
            List<Long> working = user.getJobsWorking();
            List<Long> combined = new ArrayList<>();
            combined.addAll(working);
            combined.addAll(posted);
            return combined;
        }
        return ret;
    }

    @GetMapping("/findWorkingOrdersById/{id:.+}")
    public List<Long> findWorkingJobsById(@PathVariable("id") long id){
        Optional<User> userOptional = this.userRepository.findById(id);
        List<Long> ret = new ArrayList<>();

        if(userOptional.isPresent()){
            User user = userOptional.get();
            return user.getJobsWorking();
        }
        return ret;
    }

    @GetMapping("/findPostingJobsById/{id:.+}")
    public List<Long> findPostingJobsById(@PathVariable("id") Long id){
        Optional<User> userOptional = this.userRepository.findById(id);
        List<Long> ret = new ArrayList<>();

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
    public void delete(@PathVariable("id") Long id){
        this.userRepository.deleteById(id);
    }
}
