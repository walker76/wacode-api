package api.controller;

import api.domain.auth.AuthRecord;
import api.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthRepository authRepository;

    @GetMapping("/login")
    public AuthRecord findById(@RequestBody AuthRecord record){
        Optional<AuthRecord> recordOptional = this.authRepository.findById(record.getEmail());
        return recordOptional.isPresent() ? recordOptional.get() : null;
    }

    @PutMapping("/register")
    public void register(@RequestBody AuthRecord record){
        this.authRepository.insert(record);
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
