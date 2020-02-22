package api.controller;

import api.domain.company.Company;
import api.domain.company.CompanyRequest;
import api.repository.CompanyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/all")
    public List<Company> getAll(){
        return this.companyRepository.findAll();
    }

    @GetMapping("/findById/{id:.+}")
    public Company findById(@PathVariable("id") long id){
        Optional<Company> deviceOpt = this.companyRepository.findById(id);
        return deviceOpt.isPresent() ? deviceOpt.get() : null;
    }

    @PutMapping("/register")
    public void register(@RequestBody CompanyRequest request){
        this.companyRepository.insert(new Company(request));
    }

    @PutMapping("/insert")
    public void insert(@RequestBody Company company){
        this.companyRepository.insert(company);
    }

    @PostMapping("/update")
    public void update(@RequestBody Company company){
        this.companyRepository.save(company);
    }

    @DeleteMapping("/delete/{id:.+}")
    public void delete(@PathVariable("id") Long id){
        this.companyRepository.deleteById(id);
    }
}

