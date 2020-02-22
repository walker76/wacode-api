package api.controller;

import api.domain.auth.AuthRecord;
import api.domain.user.User;
import api.exceptions.UserNotRegisteredException;
import api.repository.AuthRepository;
import api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private UserRepository userRepository;

    @PutMapping("/login")
    public User findById(@RequestBody AuthRecord record){
        System.out.println(record.getEmail() + " " + record.getPassword());
        Optional<AuthRecord> recordOptional = this.authRepository.findById(record.getEmail());
        if(recordOptional.isPresent()){
            AuthRecord recordFound = recordOptional.get();
            Optional<User> userOptional = userRepository.findDistinctByEmailIs(recordFound.getEmail());
            if(userOptional.isPresent()){
                return userOptional.get();
            } else {
                throw new UserNotRegisteredException();
            }
        } else {
            throw new UserNotRegisteredException();
        }
    }

    @PutMapping("/register")
    public User register(@RequestBody AuthRecord record){
        this.authRepository.insert(record);
        User user = new User(record.getEmail());
        userRepository.save(user);
        return user;
    }

    @PostMapping("/update")
    public void update(@RequestBody AuthRecord user){
        this.authRepository.save(user);
    }

    @DeleteMapping("/delete/{email:.+}")
    public void delete(@PathVariable("email") String email){
        this.authRepository.deleteById(email);
    }
}
