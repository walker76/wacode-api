package api.controller;

import api.domain.user.User;
import api.domain.user.UserRequest;
import api.exceptions.UserNotRegisteredException;
import api.repository.AuthRepository;
import api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserRepository userRepository;
    @Autowired private AuthRepository authRepository;

    @GetMapping("/all")
    public List<User> getAll(){
        return this.userRepository.findAll();
    }

    @GetMapping("/findById/{id:.+}")
    public User findById(@PathVariable("id") long id){
        Optional<User> userOptional = this.userRepository.findById(id);
        if(userOptional.isPresent()){
            return userOptional.get();
        } else {
            throw new UserNotRegisteredException();
        }
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
