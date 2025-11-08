package alkz.mscurriculum.repository;

import document.Verification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationsRepository extends MongoRepository<Verification, String> {
}
