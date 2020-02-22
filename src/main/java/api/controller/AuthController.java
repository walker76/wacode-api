package api.controller;

import api.domain.auth.AuthRecord;
import api.exceptions.UserNotRegisteredException;
import api.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthRepository authRepository;

    @PutMapping("/login")
    public AuthRecord findById(@RequestBody AuthRecord record){
        Optional<AuthRecord> recordOptional = this.authRepository.findById(record.getEmail());
        if(recordOptional.isPresent()){
            return recordOptional.get();
        } else {
            throw new UserNotRegisteredException();
        }
    }

    @PutMapping("/register")
    public AuthRecord register(@RequestBody AuthRecord record){
        this.authRepository.insert(record);
        return record;
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
