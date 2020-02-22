package api.controller;

import api.domain.user.User;
import api.domain.user.UserRequest;
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

    @PutMapping("/register")
    public void register(@RequestBody UserRequest request){
        this.userRepository.insert(new User(request));
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
