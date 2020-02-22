package api.repository;

import api.domain.auth.AuthRecord;
import api.domain.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends MongoRepository<AuthRecord, String> {
}
