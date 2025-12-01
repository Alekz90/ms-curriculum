package alkz.mscurriculum.repository;

import alkz.mscurriculum.document.Recovery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoveriesRepository extends MongoRepository<Recovery, String> {
}
